package com.student;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/StudentResultServlet")
public class StudentResultServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        // Get values from HTML form
        String regno = request.getParameter("regno");
        String name = request.getParameter("name");
        String department = request.getParameter("department");

        int mark1 = Integer.parseInt(request.getParameter("mark1"));
        int mark2 = Integer.parseInt(request.getParameter("mark2"));
        int mark3 = Integer.parseInt(request.getParameter("mark3"));
        int mark4 = Integer.parseInt(request.getParameter("mark4"));
        int mark5 = Integer.parseInt(request.getParameter("mark5"));

        double attendance =
                Double.parseDouble(request.getParameter("attendance"));

        // Calculate total
        int total = mark1 + mark2 + mark3 + mark4 + mark5;

        // Calculate average
        double average = total / 5.0;

        // Calculate grade
        String grade;

        if (average >= 90) {
            grade = "A+";
        } else if (average >= 80) {
            grade = "A";
        } else if (average >= 70) {
            grade = "B";
        } else if (average >= 60) {
            grade = "C";
        } else if (average >= 50) {
            grade = "D";
        } else {
            grade = "F";
        }

        // Check attendance eligibility
        boolean eligible = attendance >= 75;

        // Check subject-wise pass
        boolean subjectPass =
                mark1 >= 40 &&
                mark2 >= 40 &&
                mark3 >= 40 &&
                mark4 >= 40 &&
                mark5 >= 40;

        // Final result
        boolean pass = subjectPass && average >= 50 && eligible;

        String result;

        if (pass) {
            result = "PASS";
        } else {
            result = "FAIL";
        }

        // Display result
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();

        out.println("<html>");
        out.println("<head>");
        out.println("<title>Student Examination Result</title>");
        out.println("</head>");

        out.println("<body>");

        out.println("<h2>Student Examination Result</h2>");

        out.println("<p><b>Register Number:</b> " + regno + "</p>");
        out.println("<p><b>Student Name:</b> " + name + "</p>");
        out.println("<p><b>Department:</b> " + department + "</p>");

        out.println("<table border='1' cellpadding='10'>");

        out.println("<tr>");
        out.println("<th>Subject</th>");
        out.println("<th>Mark</th>");
        out.println("<th>Status</th>");
        out.println("</tr>");

        out.println("<tr>");
        out.println("<td>Subject 1</td>");
        out.println("<td>" + mark1 + "</td>");
        out.println("<td>" + (mark1 >= 40 ? "PASS" : "FAIL") + "</td>");
        out.println("</tr>");

        out.println("<tr>");
        out.println("<td>Subject 2</td>");
        out.println("<td>" + mark2 + "</td>");
        out.println("<td>" + (mark2 >= 40 ? "PASS" : "FAIL") + "</td>");
        out.println("</tr>");

        out.println("<tr>");
        out.println("<td>Subject 3</td>");
        out.println("<td>" + mark3 + "</td>");
        out.println("<td>" + (mark3 >= 40 ? "PASS" : "FAIL") + "</td>");
        out.println("</tr>");

        out.println("<tr>");
        out.println("<td>Subject 4</td>");
        out.println("<td>" + mark4 + "</td>");
        out.println("<td>" + (mark4 >= 40 ? "PASS" : "FAIL") + "</td>");
        out.println("</tr>");

        out.println("<tr>");
        out.println("<td>Subject 5</td>");
        out.println("<td>" + mark5 + "</td>");
        out.println("<td>" + (mark5 >= 40 ? "PASS" : "FAIL") + "</td>");
        out.println("</tr>");

        out.println("</table>");

        out.println("<h3>Total Marks: " + total + " / 500</h3>");

        out.println("<h3>Average: " + average + "</h3>");

        out.println("<h3>Grade: " + grade + "</h3>");

        out.println("<h3>Attendance: " + attendance + "%</h3>");

        out.println("<h3>Eligibility: "
                + (eligible ? "Eligible" : "Not Eligible")
                + "</h3>");

        out.println("<h2>Overall Result: " + result + "</h2>");

        out.println("<br>");

        out.println("<a href='index.html'>Back to Form</a>");

        out.println("</body>");
        out.println("</html>");
    }
}
