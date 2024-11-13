/*
 * Copyright 2024 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uk.gov.hmrc.test.api.service

import play.api.libs.ws.StandaloneWSRequest
import uk.gov.hmrc.test.api.client.HttpClient
import uk.gov.hmrc.test.api.conf.TestConfiguration
import java.time.LocalDate
import scala.concurrent.Await
import scala.concurrent.duration._

class PensionSchemeReturnOverviewService extends HttpClient {
  val host: String   = TestConfiguration.url("pension-scheme-return")
  val psrURL: String = s"$host/psr"

  def getOverviewByPstrAndDate(
    authToken: String,
    pstr: String,
    toDate: LocalDate,
    fromDate: LocalDate
  ): StandaloneWSRequest#Self#Response =
    Await.result(
      get(
        s"$psrURL/overview/$pstr?toDate=$toDate&fromDate=$fromDate",
        ("Authorization", authToken),
        ("userName", "TestUserName"),
        ("schemeName", "TestSchemeName"),
        ("CorrelationId", "12345678"),
        ("srn", "S0000000042"),
        ("Accept", "application/json")
      ),
      10.seconds
    )

  def getFormBundleEndpoint(authToken: String, pstr: String, fbNumber: String): StandaloneWSRequest#Self#Response =
    Await.result(
      get(
        s"$psrURL/standard/$pstr?fbNumber=$fbNumber",
        ("Authorization", authToken),
        ("userName", "TestUserName"),
        ("schemeName", "TestSchemeName"),
        ("srn", "S0000000042"),
        ("CorrelationId", "12345678"),
        ("Accept", "application/json")
      ),
      10.seconds
    )

  def getPSREntityUnprocessed(
    authToken: String,
    pstr: String,
    psrVersion: String,
    periodStartDate: String
  ): StandaloneWSRequest#Self#Response =
    Await.result(
      get(
        s"$psrURL/standard/$pstr?psrVersion=$psrVersion&periodStartDate=$periodStartDate",
        ("Authorization", authToken),
        ("userName", "TestUserName"),
        ("schemeName", "TestSchemeName"),
        ("srn", "S0000000042"),
        ("CorrelationId", "12345678"),
        ("Accept", "application/json")
      ),
      10.seconds
    )

  def getOverviewByPstrStartDate_BadRequest(
    authToken: String,
    pstr: String,
    startDate: String
  ): StandaloneWSRequest#Self#Response =
    Await.result(
      get(
        s"$psrURL/overview/$pstr?startDate=$startDate",
        ("Authorization", authToken),
        ("CorrelationId", "12345678"),
        ("userName", "TestUserName"),
        ("schemeName", "TestSchemeName"),
        ("srn", "S0000000042"),
        ("Accept", "application/json")
      ),
      10.seconds
    )

  def getOverviewUnauthorizedPSAUser(
    authToken: String,
    pstr: String,
    fromDate: String,
    toDate: String
  ): StandaloneWSRequest#Self#Response =
    Await.result(
      get(
        s"$psrURL/overview/$pstr?fromDate=$fromDate&toDate=$toDate",
        ("Authorization", authToken),
        ("CorrelationId", "12345678"),
        ("userName", "TestUserName"),
        ("schemeName", "TestSchemeName"),
        ("srn", "S2400000041"),
        ("requestRole", "PSA"),
        ("Accept", "application/json")
      ),
      10.seconds
    )

  def getOverviewAuthorizedPSPUser(
    authToken: String,
    pstr: String,
    fromDate: String,
    toDate: String
  ): StandaloneWSRequest#Self#Response =
    Await.result(
      get(
        s"$psrURL/overview/$pstr?fromDate=$fromDate&toDate=$toDate",
        ("Authorization", authToken),
        ("CorrelationId", "12345678"),
        ("userName", "TestUserName"),
        ("schemeName", "TestSchemeName"),
        ("srn", "S2400000041"),
        ("requestRole", "PSP"),
        ("Accept", "application/json")
      ),
      10.seconds
    )

}
