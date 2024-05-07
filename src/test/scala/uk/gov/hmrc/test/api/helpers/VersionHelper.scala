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
import play.api.libs.ws.StandaloneWSRequest
import uk.gov.hmrc.test.api.service.{PensionSchemeReturnVersionService}

class VersionHelper {

  val pensionSchemeReturnVersionsServiceAPI: PensionSchemeReturnVersionService = new PensionSchemeReturnVersionService

  def listVersionsByPstrAndDate(authBearerToken: String, pstr: String, startDate:String): Unit = {
    val versionsGetResponse: StandaloneWSRequest#Self#Response =
      pensionSchemeReturnVersionsServiceAPI.getVersionsByPstrAndDate(authBearerToken, pstr, startDate)
    versionsGetResponse.status shouldBe 200
  }

  def getNotFoundVersionsEndpoint(authBearerToken: String, pstr: String, startDate: String) = {
    val versionsGetResponse: StandaloneWSRequest#Self#Response =
      pensionSchemeReturnVersionsServiceAPI.getNotFoundVersionsEndpoint(authBearerToken, pstr, startDate)
    versionsGetResponse.status shouldBe 200
  } :Unit

  def getPSRVersionsForbiddenEndpoint(authBearerToken: String, pstr: String, startDate: String) = {
    val versionsGetResponse: StandaloneWSRequest#Self#Response =
      pensionSchemeReturnVersionsServiceAPI.getVersionsByPstr_Forbidden(authBearerToken, pstr, startDate)
    versionsGetResponse.status shouldBe 403
  }: Unit

  def getPSRVersionBadRequestEndpoint(authBearerToken: String, pstr: String, periodStartDate: String) = {
    val versionsGetResponse: StandaloneWSRequest#Self#Response =
      pensionSchemeReturnVersionsServiceAPI.getVersionsByPstrStartDate_BadRequest(authBearerToken, pstr, periodStartDate)
    versionsGetResponse.status shouldBe 400
  }: Unit
}
