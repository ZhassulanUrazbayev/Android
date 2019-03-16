package kz.production.kuanysh.tarelka.data.network.model.quizquestions;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result implements Parcelable
{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("question")
    @Expose
    private String question;
    @SerializedName("answer_a")
    @Expose
    private String answerA;
    @SerializedName("answer_b")
    @Expose
    private String answerB;
    @SerializedName("answer_c")
    @Expose
    private String answerC;
    @SerializedName("answer_d")
    @Expose
    private String answerD;
    @SerializedName("answer_e")
    @Expose
    private String answerE;
    @SerializedName("right_answer")
    @Expose
    private String rightAnswer;
    public final static Parcelable.Creator<Result> CREATOR = new Creator<Result>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Result createFromParcel(Parcel in) {
            return new Result(in);
        }

        public Result[] newArray(int size) {
            return (new Result[size]);
        }

    }
            ;

    protected Result(Parcel in) {
        this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.question = ((String) in.readValue((String.class.getClassLoader())));
        this.answerA = ((String) in.readValue((String.class.getClassLoader())));
        this.answerB = ((String) in.readValue((String.class.getClassLoader())));
        this.answerC = ((String) in.readValue((String.class.getClassLoader())));
        this.answerD = ((String) in.readValue((String.class.getClassLoader())));
        this.answerE = ((String) in.readValue((String.class.getClassLoader())));
        this.rightAnswer = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     *
     */
    public Result() {
    }

    /**
     *
     * @param id
     * @param rightAnswer
     * @param answerD
     * @param answerE
     * @param answerB
     * @param answerC
     * @param question
     * @param answerA
     */
    public Result(Integer id, String question, String answerA, String answerB, String answerC, String answerD, String answerE, String rightAnswer) {
        super();
        this.id = id;
        this.question = question;
        this.answerA = answerA;
        this.answerB = answerB;
        this.answerC = answerC;
        this.answerD = answerD;
        this.answerE = answerE;
        this.rightAnswer = rightAnswer;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswerA() {
        return answerA;
    }

    public void setAnswerA(String answerA) {
        this.answerA = answerA;
    }

    public String getAnswerB() {
        return answerB;
    }

    public void setAnswerB(String answerB) {
        this.answerB = answerB;
    }

    public String getAnswerC() {
        return answerC;
    }

    public void setAnswerC(String answerC) {
        this.answerC = answerC;
    }

    public String getAnswerD() {
        return answerD;
    }

    public void setAnswerD(String answerD) {
        this.answerD = answerD;
    }

    public String getAnswerE() {
        return answerE;
    }

    public void setAnswerE(String answerE) {
        this.answerE = answerE;
    }

    public String getRightAnswer() {
        return rightAnswer;
    }

    public void setRightAnswer(String rightAnswer) {
        this.rightAnswer = rightAnswer;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(question);
        dest.writeValue(answerA);
        dest.writeValue(answerB);
        dest.writeValue(answerC);
        dest.writeValue(answerD);
        dest.writeValue(answerE);
        dest.writeValue(rightAnswer);
    }

    public int describeContents() {
        return 0;
    }

}