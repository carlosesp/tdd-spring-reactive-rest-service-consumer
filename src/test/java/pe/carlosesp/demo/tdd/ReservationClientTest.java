package pe.carlosesp.demo.tdd;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJson;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.Arrays;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
//@AutoConfigureWireMock
@AutoConfigureStubRunner(
        ids = "pe.carlosesp.demo:tdd-spring-reactive-rest-service-producer:+:8080",
        stubsMode = StubRunnerProperties.StubsMode.LOCAL
)
@AutoConfigureJson
public class ReservationClientTest {

    @Autowired
    private ReservationClient client;

//    @Autowired
//    private ObjectMapper objectMapper;

    @Test
    public void getAllReservations() throws Exception {
//        String json = this.objectMapper
//                .writeValueAsString(Arrays.asList(new Reservation("1", "Jane"),
//                                new Reservation("2", "Joe")));
//
//        WireMock
//                .stubFor(
//                        WireMock.get(WireMock.urlEqualTo("/reservations"))
//                        .willReturn(WireMock.aResponse()
//                                .withBody(json)
//                                .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
//                                .withStatus(HttpStatus.OK.value())
//                        )
//                );

        Flux<Reservation> res = this.client.getAllReservations();

        List<String> names = Arrays.asList("Jane", "Joe");
        StepVerifier
                .create(res)
                .expectNextMatches(reservation -> names.contains(reservation.getName()))
                .expectNextMatches(reservation -> names.contains(reservation.getName()))
                .verifyComplete();
    }

}
