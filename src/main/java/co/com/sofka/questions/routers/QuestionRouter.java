package co.com.sofka.questions.routers;

import co.com.sofka.questions.model.AnswerDTO;
import co.com.sofka.questions.model.QuestionDTO;
import co.com.sofka.questions.usecases.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springdoc.core.annotations.RouterOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.function.Function;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class QuestionRouter {

    //Get all question
    @Bean
    @RouterOperation(beanClass = ListUseCase.class, beanMethod = "get",
            operation = @Operation(operationId = "listAllQuestions", summary = "List all questions", tags = {"Questions"},
                    responses = {@ApiResponse(responseCode = "200", description = "OK"),
                            @ApiResponse(responseCode = "401", description = "Unauthorized", content = { @Content(examples = { @ExampleObject(value = "")})}),
                            @ApiResponse(responseCode = "403", description = "Forbidden", content = { @Content(examples = { @ExampleObject(value = "")})}),
                            @ApiResponse(responseCode = "404", description = "Not found", content = { @Content(examples = { @ExampleObject(value = "")})})}))
    public RouterFunction<ServerResponse> getAll(ListUseCase listUseCase) {
        return route(GET("/getAll"),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(listUseCase.get(), QuestionDTO.class))
        );
    }

    //Get all questions user
    @Bean
    @RouterOperation(beanClass = OwnerListUseCase.class, beanMethod = "apply",
            operation = @Operation(operationId = "getAllQuestionsUser", summary = "Get all questions from a user", tags = {"Questions"},
                    responses = {@ApiResponse(responseCode = "200", description = "OK"),
                            @ApiResponse(responseCode = "400", description = "Invalid User ID supplied", content = { @Content(examples = { @ExampleObject(value = "")})}),
                             @ApiResponse(responseCode = "401", description = "Unauthorized", content = { @Content(examples = { @ExampleObject(value = "")})}),
                             @ApiResponse(responseCode = "403", description = "Forbidden", content = { @Content(examples = { @ExampleObject(value = "")})}),
                             @ApiResponse(responseCode = "404", description = "User not found", content = { @Content(examples = { @ExampleObject(value = "")})})}))
    public RouterFunction<ServerResponse> getOwnerAll(OwnerListUseCase ownerListUseCase) {
        return route(
                GET("/getOwnerAll/{userId}"),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(
                                ownerListUseCase.apply(request.pathVariable("userId")),
                                QuestionDTO.class
                        ))
        );
    }

    //Post question
    @Bean
    @RouterOperation(beanClass = CreateUseCase.class, beanMethod = "apply",
            operation = @Operation(operationId = "createQuestion", summary = "Create Question", tags = {"Questions"},
                    responses = {@ApiResponse(responseCode = "201", description = "Created"),
                             @ApiResponse(responseCode = "401", description = "Unauthorized", content = { @Content(examples = { @ExampleObject(value = "")})}),
                             @ApiResponse(responseCode = "403", description = "Forbidden", content = { @Content(examples = { @ExampleObject(value = "")})}),
                             @ApiResponse(responseCode = "404", description = "Not found", content = { @Content(examples = { @ExampleObject(value = "")})})}))
    public RouterFunction<ServerResponse> create(CreateUseCase createUseCase) {
        Function<QuestionDTO, Mono<ServerResponse>> executor = questionDTO -> createUseCase.apply(questionDTO)
                .flatMap(result -> ServerResponse.ok()
                        .contentType(MediaType.TEXT_PLAIN)
                        .bodyValue(result));

        return route(
                POST("/create").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(QuestionDTO.class).flatMap(executor)
        );
    }

    //Get question by id
    @Bean
    @RouterOperation(beanClass = GetUseCase.class, beanMethod = "apply",
            operation = @Operation(operationId = "getQuestionById", summary = "Get question by id", tags = {"Questions"},
                    responses = {@ApiResponse(responseCode = "200", description = "OK"),
                            @ApiResponse(responseCode = "401", description = "Unauthorized", content = { @Content(examples = { @ExampleObject(value = "")})}),
                            @ApiResponse(responseCode = "403", description = "Forbidden", content = { @Content(examples = { @ExampleObject(value = "")})}),
                            @ApiResponse(responseCode = "404", description = "Not found", content = { @Content(examples = { @ExampleObject(value = "")})})}))
    public RouterFunction<ServerResponse> get(GetUseCase getUseCase) {
        return route(
                GET("/get/{id}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(getUseCase.apply(
                                        request.pathVariable("id")),
                                QuestionDTO.class
                        ))
        );
    }

    //Post answer
    @Bean
    @RouterOperation(beanClass = AddAnswerUseCase.class, beanMethod = "apply",
            operation = @Operation(operationId = "addAnAnswer", summary = "Add an answer to a question", tags = {"Questions"},
                    responses = {@ApiResponse(responseCode = "201", description = "Created"),
                            @ApiResponse(responseCode = "401", description = "Unauthorized", content = { @Content(examples = { @ExampleObject(value = "")})}),
                            @ApiResponse(responseCode = "403", description = "Forbidden", content = { @Content(examples = { @ExampleObject(value = "")})}),
                            @ApiResponse(responseCode = "404", description = "Not found", content = { @Content(examples = { @ExampleObject(value = "")})})}))
    public RouterFunction<ServerResponse> addAnswer(AddAnswerUseCase addAnswerUseCase) {
        return route(POST("/add").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(AnswerDTO.class)
                        .flatMap(addAnswerDTO -> addAnswerUseCase.apply(addAnswerDTO)
                                .flatMap(result -> ServerResponse.ok()
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .bodyValue(result))
                        )
        );
    }

    //Delete Question
    @Bean
    @RouterOperation(beanClass = DeleteUseCase.class, beanMethod = "apply",
            operation = @Operation(operationId = "deleteQuestion", summary = "Delete question by id", tags = {"Questions"},
                    responses = {@ApiResponse(responseCode = "200", description = "OK"),
                            @ApiResponse(responseCode = "204", description = "No content", content = { @Content(examples = { @ExampleObject(value = "")})}), 
                            @ApiResponse(responseCode = "401", description = "Unauthorized", content = { @Content(examples = { @ExampleObject(value = "")})}),
                            @ApiResponse(responseCode = "403", description = "Forbidden", content = { @Content(examples = { @ExampleObject(value = "")})})}))
    public RouterFunction<ServerResponse> delete(DeleteUseCase deleteUseCase) {
        return route(
                DELETE("/delete/{id}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.accepted()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(deleteUseCase.apply(request.pathVariable("id")), Void.class))
        );
    }
}
