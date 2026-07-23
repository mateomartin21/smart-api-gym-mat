package mx.edu.tecdesoftware.smart_api_gym_mat.domain;

public class Exercise {

    private Integer exerciseId;
    private String name;
    private Integer repetitions;
    private MuscleCategory muscleCategory;
    private Routine routine;

    public Integer getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(Integer exerciseId) {
        this.exerciseId = exerciseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getRepetitions() {
        return repetitions;
    }

    public void setRepetitions(Integer repetitions) {
        this.repetitions = repetitions;
    }

    public MuscleCategory getMuscleCategory() {
        return muscleCategory;
    }

    public void setMuscleCategory(MuscleCategory muscleCategory) {
        this.muscleCategory = muscleCategory;
    }

    public Routine getRoutine() {
        return routine;
    }

    public void setRoutine(Routine routine) {
        this.routine = routine;
    }
}
