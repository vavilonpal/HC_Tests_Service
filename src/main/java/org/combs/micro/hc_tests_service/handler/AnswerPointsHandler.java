package org.combs.micro.hc_tests_service.handler;


import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.slf4j.Slf4j;
import org.combs.micro.hc_tests_service.entity.Answer;
import org.combs.micro.hc_tests_service.entity.Question;
import org.combs.micro.hc_tests_service.enums.QuestionType;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import static org.apache.commons.lang3.StringUtils.isNumeric;


// todo допилить метод для текста, все остальное вроде работает
@Component
@Slf4j
public class AnswerPointsHandler {
    private Integer rankPoints = 0;
    private Integer scorePoints = 0;

    /**
     * Метод определяет сколько нжуно дать очков за полученный ответ
     *
     * @param answer - ответ студента
     */
    public void defineAnswerCorrectness(Answer answer) {
        Question question = answer.getQuestion();

        // Если вопрос не подлежит автопроверке — очки остаются нулевыми
        if (!question.getCheckType()) {
            answer.setRankPoints(rankPoints);
            answer.setScorePoints(scorePoints);
            return;
        }

        switch (question.getType()) {
            case multiple_choice -> multiChoiceTypeHandle(answer, question);
            case single_choice -> singleChoiceTypeHandle(answer, question);
            case text -> textTypeHandle(answer, question);
        }
    }

    /** Обработка текстового ответа */
    private void textTypeHandle(Answer answer, Question question) {
        JsonNode correctNode = question.getAnswer().get("correct");
        JsonNode studentNode = answer.getStudentAnswer().get("answer");

        if (correctNode == null || !correctNode.isArray() || correctNode.isEmpty()) return;
        if (studentNode == null || !studentNode.isArray() || studentNode.isEmpty()) return;

        String correct = correctNode.get(0).asText().strip();
        String student = studentNode.get(0).asText().strip();

        if (student.isBlank()) return;

        if (isNumeric(correct) && isNumeric(student)) {
            if (Double.compare(Double.parseDouble(correct), Double.parseDouble(student)) == 0) {
                assignPoints(question);
                setPointsToAnswer(answer);
                return;
            }
        }

        if (correct.equalsIgnoreCase(student)) {
            assignPoints(question);
        }

        setPointsToAnswer(answer);
    }

    /** Обработка множественного выбора */
    private void multiChoiceTypeHandle(Answer answer, Question question) {
        JsonNode correctNode = question.getAnswer().get("correct");
        JsonNode studentNode = answer.getStudentAnswer().get("answer");

        if (correctNode == null || !correctNode.isArray()) return;

        Set<String> correctAnswers = new HashSet<>();
        for (JsonNode node : correctNode) {
            correctAnswers.add(node.asText());
        }

        int correctTotal = correctAnswers.size();

        Set<String> studentAnswers = new HashSet<>();
        if (studentNode != null && studentNode.isArray()) {
            for (JsonNode node : studentNode) {
                studentAnswers.add(node.asText());
            }
        }

        if (studentAnswers.isEmpty()) return;

        int correctMatches = 0;
        for (String studentAnswer : studentAnswers) {
            if (correctAnswers.contains(studentAnswer)) {
                correctMatches++;
            } else {
                correctMatches--;
            }
        }

        calculatePoints(correctTotal, correctMatches, question);
        setPointsToAnswer(answer);
    }

    /** Обработка одиночного выбора */
    private void singleChoiceTypeHandle(Answer answer, Question question) {
        JsonNode correctNode = question.getAnswer().get("correct");
        log.info(correctNode.toString());
        JsonNode studentNode = answer.getStudentAnswer().get("answer");

        if (correctNode == null || !correctNode.isArray() || correctNode.isEmpty()) return;
        if (studentNode == null || !studentNode.isArray() || studentNode.isEmpty()) return;

        String correctStr = correctNode.get(0).asText().trim();
        String studentStr = studentNode.get(0).asText().trim();

        if (studentStr.isBlank()) return;

        if (isNumeric(correctStr) && isNumeric(studentStr)) {
            try {
                double correctNum = Double.parseDouble(correctStr);
                double studentNum = Double.parseDouble(studentStr);

                if (Double.compare(correctNum, studentNum) == 0) {
                    assignPoints(question);
                }
            } catch (NumberFormatException e) {
                System.out.println("NumberFormatException: " + e.getMessage());
            }
        } else if (correctStr.equalsIgnoreCase(studentStr)) {
            assignPoints(question);
        }

        setPointsToAnswer(answer);
    }

    /** Вычисление очков по проценту правильных ответов */
    private void calculatePoints(int correctCount, int studentCorrectCount, Question question) {
        if (correctCount == studentCorrectCount) {
            assignPoints(question);
            return;
        }

        if (studentCorrectCount < 0) return;

        float ratio = correctCount > 0 ? (float) studentCorrectCount / correctCount : 0;

        this.rankPoints = Math.round(question.getRankPoints() * ratio);
        this.scorePoints = Math.round(question.getScorePoints() * ratio);
    }




    private static boolean isNumeric(String str) {
        return str != null && str.matches("-?\\d+(\\.\\d+)?");
    }

    private void setPointsToAnswer(Answer answer) {
        answer.setRankPoints(rankPoints);
        answer.setScorePoints(scorePoints);
    }

    private void assignPoints(Question question) {
        this.rankPoints = question.getRankPoints();
        this.scorePoints = question.getScorePoints();
    }



}


