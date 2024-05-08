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

package uk.gov.hmrc.test.api.models

import play.api.libs.json.{Format, Json}

import java.time.LocalDate


case class Overview(
                     periodStartDate: LocalDate,
                     periodEndDate: LocalDate,
                     numberOfVersions: Option[Int],
                     submittedVersionAvailable: Option[String],
                     compiledVersionAvailable: Option[String],
                     tpssReportPresent: Option[String],
                     ntfDateOfIssue: Option[LocalDate],
                     psrDueDate: Option[LocalDate],
                     psrReportType: Option[String]
                   )

object Overview {
  implicit val formats: Format[Overview] = Json.format[Overview]

  val overviews: Seq[Overview] = Seq(
    Overview(periodStartDate = LocalDate.of(2023, 4, 6), periodEndDate = LocalDate.of(2024, 4, 5), numberOfVersions = Some(0), submittedVersionAvailable = Some("No"), compiledVersionAvailable = Some("No"), tpssReportPresent = None, ntfDateOfIssue = Some(LocalDate.of(2023, 11, 10)), psrDueDate = Some(LocalDate.of(2024, 3, 31)), psrReportType = Some("Standard")),
    Overview(periodStartDate = LocalDate.of(2022, 4, 6), periodEndDate = LocalDate.of(2023, 4, 5), numberOfVersions = Some(1), submittedVersionAvailable = Some("No"), compiledVersionAvailable = Some("Yes"), tpssReportPresent = None, ntfDateOfIssue = Some(LocalDate.of(2022, 12, 6)), psrDueDate = Some(LocalDate.of(2024, 3, 31)), psrReportType = Some("Standard")),
    Overview(periodStartDate = LocalDate.of(2021, 4, 6), periodEndDate = LocalDate.of(2022, 4, 5), numberOfVersions = Some(4), submittedVersionAvailable = Some("Yes"), compiledVersionAvailable = Some("Yes"), tpssReportPresent = None, ntfDateOfIssue = Some(LocalDate.of(2021, 12, 6)), psrDueDate = Some(LocalDate.of(2022, 3, 31)), psrReportType = Some("Standard"))
  )
}