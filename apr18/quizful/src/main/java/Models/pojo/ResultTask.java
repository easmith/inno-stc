package Models.pojo;

/**
 * Created by eku on 13.04.17.
 */
public class ResultTask {

    private int id;

    private int taskId;
    private Task task;

    public ResultTask(int id, int taskId, int resultId) {
        this.id = id;
        this.taskId = taskId;
        this.resultId = resultId;
    }

    private int resultId;
    private Result result;

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public int getResultId() {
        return resultId;
    }

    public void setResultId(int resultId) {
        this.resultId = resultId;
    }
}
