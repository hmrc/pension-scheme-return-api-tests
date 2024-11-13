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

package uk.gov.hmrc.test.api.helpers

import org.scalatest.matchers.should.Matchers.convertToAnyShouldWrapper
import play.api.libs.json.Json
import play.api.libs.ws.StandaloneWSRequest
import uk.gov.hmrc.test.api.models.Overview
import uk.gov.hmrc.test.api.service.PensionSchemeReturnOverviewService
import java.time.LocalDate

class OverviewHelper {

  val pensionSchemeReturnServiceAPI: PensionSchemeReturnOverviewService = new PensionSchemeReturnOverviewService

  def listOverviewsByPstrAndDate(
    authBearerToken: String,
    pstr: String,
    toDate: LocalDate,
    fromDate: LocalDate
  ): Seq[Overview] = {
    val overviewsGetResponse: StandaloneWSRequest#Self#Response =
      pensionSchemeReturnServiceAPI.getOverviewByPstrAndDate(authBearerToken, pstr, toDate, fromDate)
    Json.parse(overviewsGetResponse.body).as[Seq[Overview]]
  }

  def formBundleEndpoint(authBearerToken: String, pstr: String, fbNumber: String) = {
    val formBundleGetResponse: StandaloneWSRequest#Self#Response =
      pensionSchemeReturnServiceAPI.getFormBundleEndpoint(authBearerToken, pstr, fbNumber)
    formBundleGetResponse.status shouldBe 200
  }: Unit

  def getPSREntityUnprocessedEndpoint(
    authBearerToken: String,
    pstr: String,
    psrVersion: String,
    periodStartDate: String
  ) = {
    val formBundleGetResponse: StandaloneWSRequest#Self#Response =
      pensionSchemeReturnServiceAPI.getPSREntityUnprocessed(authBearerToken, pstr, psrVersion, periodStartDate)
    formBundleGetResponse.status shouldBe 422
  }: Unit

  def getPSROverviewBadRequestEndpoint(authBearerToken: String, pstr: String, periodStartDate: String) = {
    val formBundleGetResponse: StandaloneWSRequest#Self#Response =
      pensionSchemeReturnServiceAPI.getOverviewByPstrStartDate_BadRequest(authBearerToken, pstr, periodStartDate)
    formBundleGetResponse.status shouldBe 400
  }: Unit

  def getPSRUnauthorizedOverviewEndpoint(
    authBearerToken: String,
    pstr: String,
    periodFromDate: String,
    periodEndDate: String
  ) = {
    val formBundleGetResponse: StandaloneWSRequest#Self#Response =
      pensionSchemeReturnServiceAPI.getOverviewUnauthorizedPSAUser(authBearerToken, pstr, periodFromDate, periodEndDate)
    formBundleGetResponse.status shouldBe 401
  }: Unit

  def getPSPAuthorizedOverviewEndpoint(
    authBearerToken: String,
    pstr: String,
    periodFromDate: String,
    periodEndDate: String
  ) = {
    val formBundleGetResponse: StandaloneWSRequest#Self#Response =
      pensionSchemeReturnServiceAPI.getOverviewAuthorizedPSPUser(authBearerToken, pstr, periodFromDate, periodEndDate)
    formBundleGetResponse.status shouldBe 200
  }: Unit
}
