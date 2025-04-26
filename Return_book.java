import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

public class ReturnBook extends HttpServlet {

    private static class Book {
        String title;
        String author;
        String isbn;
        String issuedTo;

        public Book(String title, String author, String isbn, String issuedTo) {
            this.title = title;
            this.author = author;
            this.isbn = isbn;
            this.issuedTo = issuedTo;
        }
    }

    // List of books in the library (hardcoded for simplicity)
    static List<Book> books = Arrays.asList(
        new Book("Book One", "Author One", "12345", null),
        new Book("Book Two", "Author Two", "67890", "User1"),
        new Book("Book Three", "Author Three", "11223", null),
        new Book("Book Four", "Author Four", "44556", "User2")
    );

    // Method to return a book and calculate fine if it's late
    public static String returnBook(String isbn, int lateDays) {
        for (Book book : books) {
            if (book.isbn.equals(isbn)) {
                if (book.issuedTo != null) {
                    // Calculate fine if the book was late
                    int finePerDay = 5;
                    int fine = finePerDay * lateDays;

                    // Return the book
                    book.issuedTo = null;

                    return "Book with ISBN " + isbn + " returned successfully. Fine: " + fine + " units.";
                } else {
                    return "Book with ISBN " + isbn + " was not issued.";
                }
            }
        }
        return "Book with ISBN " + isbn + " not found.";
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String isbn = request.getParameter("isbn");
        int lateDays = Integer.parseInt(request.getParameter("lateDays"));
        
        String result = returnBook(isbn, lateDays);
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h3>" + result + "</h3>");
        out.println("</body></html>");
    }
}
