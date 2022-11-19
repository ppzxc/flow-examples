package org.example.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.SQLException;
import org.example.entity.Member;
import org.h2.tools.Server;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class HelloServiceTest {

  private Server server;
  private HelloService<Member> helloService;

  @BeforeEach
  void setUp() throws SQLException {
    server = Server.createTcpServer().start();
    helloService = new MemberServiceImpl();
  }

  @AfterEach
  void tearDown() {
    server.stop();
  }

  @Test
  void should_inserted_when_called_add_member() {
    // given
    Member given = new Member();
    given.setName("testUSer");

    // when
    Member expected = helloService.add(given);
    Member actual = helloService.findById(given.getId()).get();

    // then
    assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
  }
}