package http;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import interfaces.TaskManager;
import manager.FileBackedTasksManager;
import manager.Managers;
import tasks.Epic;
import tasks.Subtask;
import tasks.Task;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Optional;

public class HttpTaskServer {

    private HttpServer server;
    private static final int PORT = 8080;
    private static final Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;
    private static final Gson gson = new Gson();
    static String pathFile = "test.csv";
    private static final FileBackedTasksManager managerBack = new FileBackedTasksManager(pathFile);

    public HttpTaskServer() throws IOException {

    //    this.manager = manager;

        server = HttpServer.create();

        server.bind(new InetSocketAddress(PORT), 0);

        server.createContext("/tasks", new TasksHandler());

    }
    public void start() {

        server.start();
        System.out.println("HTTP-сервер запущен на " + PORT + " порту!");

    }

    public void stop() {

        server.stop(0);
        System.out.println("HTTP-сервер остановлен на " + PORT + " порту!");

    }

    public static void main(String[] args) throws IOException, InterruptedException {

        HttpServer httpServer = HttpServer.create();
        httpServer.bind(new InetSocketAddress(PORT),0);
        httpServer.createContext("/tasks", new TasksHandler());
        httpServer.start();

        System.out.println("HTTP-сервер запущен на " + PORT + " порту.");
    }

    static class TasksHandler implements HttpHandler {

        @Override
        public void handle(HttpExchange exchange) throws IOException {
            Endpoint endpoint = getEndpoint(exchange.getRequestURI().getPath(),exchange.getRequestURI().getQuery(),exchange.getRequestMethod());
            switch (endpoint) {
                case GET_PRIORITIZED_TASKS: {
                    handleGetPrioritizedTasks(exchange);
                    break;
                }
                case GET_TASKS: {
                    handleGetTasks(exchange);
                    break;
                }
                case GET_EPICS: {
                    handleGetEpics(exchange);
                    break;
                }
                case GET_SUBTASKS: {
                    handleGetSubtasks(exchange);
                    break;
                }
                case GET_TASK_BY_ID: {
                    handleGetTaskById(exchange);
                    break;
                }
                case GET_EPIC_BY_ID: {
                    handleGetEpicById(exchange);
                    break;
                }
                case GET_SUBTASK_BY_ID: {
                    handleGetSubtaskById(exchange);
                    break;
                }
                case POST_TASK: {
                    handlePostTask(exchange);
                    break;
                }
                case POST_EPIC: {
                    handlePostEpic(exchange);
                    break;
                }
                case POST_SUBTASK: {
                    handlePostSubtask(exchange);
                    break;
                }
                case DELETE_TASKS: {
                    handleDeleteTasks(exchange);
                    break;
                }
                case DELETE_EPICS: {
                    handleDeleteEpics(exchange);
                    break;
                }
                case DELETE_SUBTASKS: {
                    handleDeleteSubtasks(exchange);
                    break;
                }
                case DELETE_TASK: {
                    handleDeleteTaskById(exchange);
                    break;
                }
                case DELETE_EPIC: {
                    handleDeleteEpicById(exchange);
                    break;
                }
                case DELETE_SUBTASK: {
                    handleDeleteSubtaskById(exchange);
                    break;
                }
                case GET_HISTORY: {
                    handleGetHistory(exchange);
                    break;
                }
                default:
                    writeResponse(exchange,"Такого эндпоинта не существует", 404);
            }
        }

        private void handleGetPrioritizedTasks(HttpExchange exchange) throws IOException {
            writeResponse(exchange, gson.toJson(managerBack.getPrioritizedTasks()), 200);
        }
        private void handleGetTasks(HttpExchange exchange) throws IOException {
            writeResponse(exchange, gson.toJson(managerBack.getListTasks()), 200);
        }
        private void handleGetEpics(HttpExchange exchange) throws IOException {
            writeResponse(exchange, gson.toJson(managerBack.getListEpics()), 200);
        }
        private void handleGetSubtasks(HttpExchange exchange) throws IOException {
            writeResponse(exchange, gson.toJson(managerBack.getListSubtasks()), 200);
        }
        private void handleGetTaskById(HttpExchange exchange) throws IOException {
            Optional<Integer> taskIdOpt = getTaskId(exchange);
            if (taskIdOpt.isEmpty()) {
                writeResponse(exchange, "Некорректный идентификатор задачи", 400);
                return;
            }
            int taskId = taskIdOpt.get();
            String taskJson = gson.toJson(managerBack.getTaskById(taskId));
            writeResponse(exchange, taskJson, 200);
        }
        private void handleGetEpicById(HttpExchange exchange) throws IOException {
            Optional<Integer> taskIdOpt = getTaskId(exchange);
            if (taskIdOpt.isEmpty()) {
                writeResponse(exchange, "Некорректный идентификатор задачи", 400);
                return;
            }
            int taskId = taskIdOpt.get();
            String taskJson = gson.toJson(managerBack.getEpicById(taskId));
            writeResponse(exchange, taskJson, 200);
        }
        private void handleGetSubtaskById(HttpExchange exchange) throws IOException {
            Optional<Integer> taskIdOpt = getTaskId(exchange);
            if (taskIdOpt.isEmpty()) {
                writeResponse(exchange, "Некорректный идентификатор задачи", 400);
                return;
            }
            int taskId = taskIdOpt.get();
            String taskJson = gson.toJson(managerBack.getSubtaskById(taskId));
            writeResponse(exchange, taskJson, 200);
        }
        private void handlePostTask(HttpExchange exchange) throws IOException {
            InputStream is = exchange.getRequestBody();
            String body = new String(is.readAllBytes(), DEFAULT_CHARSET);
            Task newTask;
            try {
                newTask = gson.fromJson(body, Task.class);
            }
            catch (JsonSyntaxException ex) {
                writeResponse(exchange, "Получен некорректный JSON", 400);
                return;
            }
            managerBack.createTask(newTask);
            writeResponse(exchange, "Задача добавлена", 201);
        }
        private void handlePostEpic(HttpExchange exchange) throws IOException {
            InputStream is = exchange.getRequestBody();
            String body = new String(is.readAllBytes(), DEFAULT_CHARSET);
            Epic newEpic;
            try {
                newEpic = gson.fromJson(body, Epic.class);
            }
            catch (JsonSyntaxException ex) {
                writeResponse(exchange, "Получен некорректный JSON", 400);
                return;
            }
            managerBack.createEpic(newEpic);
            writeResponse(exchange, "Задача добавлена", 201);
        }
        private void handlePostSubtask(HttpExchange exchange) throws IOException {
            InputStream is = exchange.getRequestBody();
            String body = new String(is.readAllBytes(), DEFAULT_CHARSET);
            Subtask newSubtask;
            try {
                newSubtask = gson.fromJson(body, Subtask.class);
            }
            catch (JsonSyntaxException ex) {
                writeResponse(exchange, "Получен некорректный JSON", 400);
                return;
            }
            managerBack.createSubtask(newSubtask);
            writeResponse(exchange, "Задача добавлена", 201);
        }
        private void handleDeleteTasks(HttpExchange exchange) throws IOException {
            managerBack.deleteTasks();
            writeResponse(exchange, "Задачи удалены", 200);
        }
        private void handleDeleteEpics(HttpExchange exchange) throws IOException {
            managerBack.deleteEpics();
            writeResponse(exchange, "Эпики удалены", 200);
        }
        private void handleDeleteSubtasks(HttpExchange exchange) throws IOException {
            managerBack.deleteSubtasks();
            writeResponse(exchange, "Сабтаски удалены", 200);
        }
        private void handleDeleteTaskById(HttpExchange exchange) throws IOException {
            Optional<Integer> taskIdOpt = getTaskId(exchange);
            if (taskIdOpt.isEmpty()) {
                writeResponse(exchange, "Некорректный идентификатор задачи", 400);
                return;
            }
            int taskId = taskIdOpt.get();
            managerBack.deleteTaskById(taskId);
            writeResponse(exchange, "Задача удалена", 200);
        }
        private void handleDeleteEpicById(HttpExchange exchange) throws IOException {
            Optional<Integer> taskIdOpt = getTaskId(exchange);
            if (taskIdOpt.isEmpty()) {
                writeResponse(exchange, "Некорректный идентификатор задачи", 400);
                return;
            }
            int taskId = taskIdOpt.get();
            managerBack.deleteEpicById(taskId);
            writeResponse(exchange, "Эпик удален", 200);
        }
        private void handleDeleteSubtaskById(HttpExchange exchange) throws IOException {
            Optional<Integer> taskIdOpt = getTaskId(exchange);
            if (taskIdOpt.isEmpty()) {
                writeResponse(exchange, "Некорректный идентификатор задачи", 400);
                return;
            }
            int taskId = taskIdOpt.get();
            managerBack.deleteSubtaskById(taskId);
            writeResponse(exchange, "Сабтаск удален", 200);
        }
        private void handleGetHistory(HttpExchange exchange) throws IOException {
            writeResponse(exchange, gson.toJson(managerBack.getHistory()), 200);
        }
        private Endpoint getEndpoint(String requestPath, String requestQuery, String requestMethod) {
            String[] pathParts = requestPath.split("/");
            switch (requestMethod) {
                case "GET":
                    switch (pathParts.length) {
                        case 2:
                            return Endpoint.GET_PRIORITIZED_TASKS;
                        case 3:
                            if (pathParts[2].equals("task") && requestQuery == null) return Endpoint.GET_TASKS;
                            if (pathParts[2].equals("task") && requestQuery != null) return Endpoint.GET_TASK_BY_ID;
                            if (pathParts[2].equals("epic") && requestQuery == null) return Endpoint.GET_EPICS;
                            if (pathParts[2].equals("epic") && requestQuery != null) return Endpoint.GET_EPIC_BY_ID;
                            if (pathParts[2].equals("subtask") && requestQuery == null) return Endpoint.GET_SUBTASKS;
                            if (pathParts[2].equals("subtask") && requestQuery != null) return Endpoint.GET_SUBTASK_BY_ID;
                            if (pathParts[2].equals("history")) return Endpoint.GET_HISTORY;
                        case 4:
                            return Endpoint.GET_EPIC_SUBTASKS;
                    }
                case "POST":
                    if (pathParts[2].equals("task")) return Endpoint.POST_TASK;
                    if (pathParts[2].equals("epic")) return Endpoint.POST_EPIC;
                    if (pathParts[2].equals("subtask")) return Endpoint.POST_SUBTASK;
                case "DELETE":
                    if (pathParts[2].equals("task") && requestQuery == null) return Endpoint.DELETE_TASKS;
                    if (pathParts[2].equals("task") && requestQuery != null) return Endpoint.DELETE_TASK;
                    if (pathParts[2].equals("epic") && requestQuery == null) return Endpoint.DELETE_EPICS;
                    if (pathParts[2].equals("epic") && requestQuery != null) return Endpoint.DELETE_EPIC;
                    if (pathParts[2].equals("subtask") && requestQuery == null) return Endpoint.DELETE_SUBTASKS;
                    if (pathParts[2].equals("subtask") && requestQuery != null) return Endpoint.DELETE_SUBTASK;
            }
            return Endpoint.UNKNOWN;
        }
        private Optional<Integer> getTaskId (HttpExchange exchange) {
        //    String[] pathParts = exchange.getRequestURI().getPath().split("/");
            String query = exchange.getRequestURI().getQuery();
            String id = query.substring(3);
            try {
                return Optional.of(Integer.parseInt(id));
            } catch (NumberFormatException ex) {
                return Optional.empty();
            }
        }
        private void writeResponse(HttpExchange exchange, String responseString, int responseCode) throws  IOException {
            if(responseString.isBlank()) {
                exchange.sendResponseHeaders(responseCode,0);
            } else {
                byte[] bytes = responseString.getBytes(DEFAULT_CHARSET);
                exchange.sendResponseHeaders(responseCode, bytes.length);
                try (OutputStream os = exchange.getResponseBody()) {
                    os.write(bytes);
                }
            }
            exchange.close();
        }

    }
}
