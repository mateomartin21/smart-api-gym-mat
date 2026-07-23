# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project overview

Spring Boot 4.1 REST API (Gradle, Java 21) for a gym domain: users, muscle categories, routines and exercises. This is a class project (ADA series) built incrementally, one architectural layer per assignment — persistence layer first, then domain/mapper layer, more layers to follow (services, controllers). A sibling reference project, `market-backend-2026-3A`, uses the same conventions and is the source of truth when a pattern here looks ambiguous.

## Commands

Run all commands from the project root (where `build.gradle` lives), using the Gradle wrapper:

- Build / compile: `./gradlew compileJava` (or `.\gradlew.bat compileJava` on Windows cmd/PowerShell)
- Full build (compile + test + assemble): `./gradlew build`
- Run the app: `./gradlew bootRun`
- Run all tests: `./gradlew test`
- Run a single test class: `./gradlew test --tests "mx.edu.tecdesoftware.smart_api_gym_mat.SmartApiGymMatApplicationTests"`

MapStruct mapper implementations (`*MapperImpl`) are generated at compile time via annotation processing into `build/generated/sources/annotationProcessor/java/main/...`. After adding or changing a `@Mapper` interface, run `./gradlew compileJava` and check that file to confirm the generated code and field mappings look right — there is no other way to see MapStruct's output.

The app runs against PostgreSQL. Connection settings live in `src/main/resources/application-dev.properties` (active profile is `dev`, set in `application.properties`); a local Postgres instance on `localhost:5432` with a database named `smart-api-gym-mat` is expected for `bootRun`/integration-style testing.

## Architecture

Package root: `mx.edu.tecdesoftware.smart_api_gym_mat`.

The codebase is layered, and the layering is deliberately kept strict — this is the main thing to preserve when adding code:

- **`entity/`** — JPA entities (`Usuario`, `CategoriaMuscular`, `Rutina`, `Ejercicio`). Spanish names and field names, mirroring the DB schema. Annotated with `@Entity`/`@Table`/`@Column`/relationship annotations. These must never be exposed outside the persistence layer.
- **`repository/`** — one `CrudRepository<Entity, Integer>` per entity, annotated `@Repository`. Thin, no custom query methods yet.
- **`domain/`** — plain POJOs with **no JPA annotations at all**. English names, translated from the Spanish entities (`Usuario`→`User`, `CategoriaMuscular`→`MuscleCategory`, `Rutina`→`Routine`, `Ejercicio`→`Exercise`). Field names are also translated/renamed (e.g. `id`→`userId`, `nombre`→`name`, `objetivo`→`objective`). This is the model the future service/controller layers are expected to work with — entities stay confined to persistence.
- **`mapper/`** — one MapStruct interface per entity/domain pair (`UserMapper`, `MuscleCategoryMapper`, `RoutineMapper`, `ExerciseMapper`), `@Mapper(componentModel = "spring")` so they're injectable Spring beans.

### Entity/domain relationships

- `Usuario` (1) → `Rutina` (many) → `Ejercicio` (many); `Ejercicio` also belongs to one `CategoriaMuscular`.
- Domain mirrors the "child embeds parent" shape, not "parent embeds children list": `Routine` embeds a `User`; `Exercise` embeds both `MuscleCategory` and `Routine`. `User`, `MuscleCategory` and `Routine` do **not** embed their child collections.

### Mapper conventions (must match `market-backend-2026-3A`)

- Every field, even when the name doesn't change, is mapped explicitly in a `@Mappings({ @Mapping(source=..., target=...), ... })` block on the entity→domain method.
- The domain→entity (reverse) method uses `@InheritInverseConfiguration` instead of restating the mappings, plus any extra `@Mapping(target = "...", ignore = true)` needed for entity-only fields (typically the back-reference collection, e.g. `rutinas`, `ejercicios`, which don't exist in the domain model).
- When an entity/domain has a nested relation to another mapped entity/domain, compose mappers via `@Mapper(..., uses = {OtherMapper.class})` rather than duplicating logic. Composition is one-directional to avoid circular mapper dependencies: `RoutineMapper` uses `UserMapper` (not vice versa); `ExerciseMapper` uses `MuscleCategoryMapper` and `RoutineMapper` (not vice versa).
- Each mapper also exposes a bulk `List<Domain> toDomains(List<Entity> entities)` method alongside the singular one.

## Reference project: market-backend-2026-3A

This project is one of a series of course assignments (ADA 1, 2, 3...) that build up the same layered architecture taught in class, first demonstrated in a separate repo: **`market-backend-2026-3A`** (GitHub: `https://github.com/mateomartin21/Market-Backend-Demo`, locally available under `OneDrive/Documentos/ProgramacionAplicada/`). When a convention here is ambiguous or a new layer needs to be added, check that project first — it is consistently the source of truth for "how the professor wants this done."

It is architecturally ahead of this project (already has services and controllers), so its layout previews what smart-api-gym-mat is likely to grow into:

- `persistence.entity` — JPA entities (equivalent to this project's `entity/`).
- `persistence.crud` — plain `CrudRepository`/`JpaRepository` interfaces for raw DB access (e.g. `ProductoCrudRepository`), equivalent to this project's `repository/`.
- `persistence.mapper` — MapStruct mappers (equivalent to this project's `mapper/`).
- `persistence.<Entity>Repository` — a **repository facade class** (e.g. `ProductoRepository implements ProductRepository`) that wires the CRUD repository and the mapper together, so callers only ever see domain objects, never entities.
- `domain` — plain domain models (equivalent to this project's `domain/`).
- `domain.repository` — repository **interfaces** expressed in domain terms (e.g. `ProductRepository`, returning `List<Product>`/`Optional<Product>`), implemented by the persistence facade above.
- `domain.service` — `@Service` classes holding business logic, depending only on the `domain.repository` interface (never on the CRUD repository or entities directly).
- `web.controller` — `@RestController`s that talk directly in domain objects (no separate request/response DTOs), documented with springdoc/Swagger annotations (`@Tag`, `@Operation`, `@ApiResponse`, `@Parameter`, example request bodies via `@ExampleObject`).

Known deviation to be aware of: this project currently keeps `entity/` and `mapper/` at the package root, while market-backend nests the equivalents under `persistence.*`. Don't "fix" this unprompted — mirror whichever layout the current assignment's instructions call for.

## Commit convention

All commits — past and future — follow [Conventional Commits](https://www.conventionalcommits.org/en/v1.0.0/) with English messages (e.g. `feat: ...`, `refactor: ...`).
