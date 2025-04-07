package org.combs.micro.hc_tests_service.handler;


import org.combs.micro.hc_tests_service.entity.Answer;
import org.combs.micro.hc_tests_service.entity.Question;
import org.combs.micro.hc_tests_service.enums.QuestionType;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;


// todo допилить метод для текста, все остальное вроде работает
@Component
public class AnswerPointsHandler {
    private Integer rankPoints = 0;
    private Integer scorePoints = 0;

    /**
     * Метод определяет сколько нжуно дать очков за полученный ответ
     * @param answer - ответ студента
     */
    public void defineAnswerCorrectness(Answer answer) {
        Question question = answer.getQuestion();
        // Если стоит флаг "не проверяемый", то оставляем выдачу очков на усмотрение учителя
        if (question.getCheckType()) {
            answer.setRankPoints(rankPoints);
            answer.setScorePoints(scorePoints);
        }

        if (question.getType().equals(QuestionType.multiple_choice)) {
            multiChoiceTypeHandle(answer, question);
        }
        if (question.getType().equals(QuestionType.single_choice)) {
            singleChoiceTypeHandle(answer, question);
        }
        if (question.getType().equals(QuestionType.text)) {
            textTypeHandle(answer, question);
        }
    }

    /**
     * Метод призван обрбатывать ответ типа "text"
     * @param answer - ответ студента
     * @param question - вопрос, на который был дан ответ, нужен для сравнения и нащанчения очков
     */
    private void textTypeHandle(Answer answer, Question question) {
        String correctAnswer = (String) question
                .getAnswer()
                .get("correct")
                .get(0);

        String studentAnswer = (String) answer
                .getStudentAnswer()
                .get("answer")
                .get(0);

        if (studentAnswer.isBlank()) {
            return;
        }
        // Если ответ числовой
        if (isNumeric(correctAnswer)) {
            if (Integer.parseInt(correctAnswer) == Integer.parseInt(studentAnswer)) {
                this.rankPoints = question.getRankPoints();
                this.scorePoints = question.getScorePoints();
                return;
            }
            ;
        }
        // Если ответ строковой
        if (correctAnswer.equalsIgnoreCase(studentAnswer.strip())) {
            this.rankPoints = question.getRankPoints();
            this.scorePoints = question.getScorePoints();
            return;
        }
        ;
    }



    /**
     * Метод призван обрбатывать ответ типа "multi-Choice"
     * @param answer - ответ студента
     * @param question - вопрос, на который был дан ответ, нужен для сравнения и нащанчения очков
     */
    private void multiChoiceTypeHandle(Answer answer, Question question) {
        int countOfCorrectAnswers;
        Set<String> correctAnswers = question.getAnswer().get("correct").stream()
                .map(Objects::toString)
                .collect(Collectors.toSet());
        countOfCorrectAnswers = correctAnswers.size();

        Set<String> studentAnswers = answer.getStudentAnswer().get("answer").stream()
                .map(Objects::toString)
                .collect(Collectors.toSet());

        if (studentAnswers.isEmpty()) {
            return;
        }

        int  countOfStudentCorrectAnswers = 0;
        for (String studentAnswer : studentAnswers) {
            if (correctAnswers.contains(studentAnswer)) {
                countOfStudentCorrectAnswers++;
            }else {
                countOfStudentCorrectAnswers--;
            }
        }

        calculatePoints(countOfCorrectAnswers, countOfStudentCorrectAnswers, question);
        setPointsToAnswer(answer);

    }


    /**
     * Метод призван обрбатывать ответ типа "single-Choice"
     * @param answer - ответ студента
     * @param question - вопрос, на который был дан ответ, нужен для сравнения и нащанчения очков
     */
    private void singleChoiceTypeHandle(Answer answer, Question question) {
        Object correctAnswer = question.getAnswer().get("correct").get(0);
        Object studentAnswer = answer.getStudentAnswer().get("answer").get(0);

        if (studentAnswer == null || studentAnswer.toString().isBlank()) {
            return;
        }

        String correctStr = correctAnswer.toString().trim();
        String studentStr = studentAnswer.toString().trim();

        // Попытка сравнения как чисел
        if (isNumeric(correctStr) && isNumeric(studentStr)) {
            try {
                double correctNum = Double.parseDouble(correctStr);
                double studentNum = Double.parseDouble(studentStr);

                if (Double.compare(correctNum, studentNum) == 0) {
                    assignPoints(question);
                    setPointsToAnswer(answer);
                }
                // при неправильном ответе ставим нулевые значения
                setPointsToAnswer(answer);
            } catch (NumberFormatException e) {
                System.out.println("NumberFormatException");
            }
            return;
        }

        // Строковое сравнение
        if (correctStr.equalsIgnoreCase(studentStr)) {
            assignPoints(question);
            setPointsToAnswer(answer);
        }
        setPointsToAnswer(answer);
    }


    /**
     * Метод вычисляет процент правлиьных ответов
     * и на его основе устанавливает количество очков
     * @param countOfCorrectAnswers - число правилньых ответов вопроса
     * @param countOfStudentCorrectAnswers - число правильных ответов данныз студентом
     * @param question - вопрос на который был дан ответ
     */
    private void calculatePoints(int countOfCorrectAnswers, int countOfStudentCorrectAnswers, Question question) {

        if (countOfCorrectAnswers == countOfStudentCorrectAnswers){
            assignPoints(question);
        }
        // Если выбрано больше неправильных овтетов чем правлиьных, то оставляем нулевые очки
        if (countOfStudentCorrectAnswers < 0){
            return;
        }

        float correctnessOfAnswers = 0.0f;
        if (countOfCorrectAnswers > 0) {
            correctnessOfAnswers = ((float) countOfStudentCorrectAnswers / countOfCorrectAnswers);
        }

        this.rankPoints = Math.round(question.getRankPoints() * correctnessOfAnswers);
        this.scorePoints = Math.round(question.getScorePoints() * correctnessOfAnswers);
    }


    private void assignPoints(Question question) {
        this.rankPoints = question.getRankPoints();
        this.scorePoints = question.getScorePoints();
    }

    private void setPointsToAnswer(Answer answer) {
        answer.setRankPoints(rankPoints);
        answer.setScorePoints(scorePoints);
    }

    private static boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");
    }


}
