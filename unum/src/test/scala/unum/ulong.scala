/*
 * Copyright 2022 Alex Henning Johannessen
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

package unum

import java.lang.{Long => JLong}
import cats.syntax.all._
import cats.kernel.laws.discipline._
import munit._
import org.scalacheck._
import org.scalacheck.Prop.forAll

class ULongSuite extends DisciplineSuite {

  implicit val ulong: Arbitrary[ULong] = Arbitrary(Arbitrary.arbitrary[Long].map(l => ULong(l)))
  implicit val cogen: Cogen[ULong]     = Cogen[BigInt].contramap[ULong](_.toBigInt)

  //

  property("n >= 0") {
    forAll((n: ULong) => n >= ULong.min)
  }

  property("a < b") {
    forAll((a: ULong, b: ULong) => a < b == a.toBigInt < b.toBigInt)
  }

  property("a <= b") {
    forAll((a: ULong, b: ULong) => a <= b == a.toBigInt <= b.toBigInt)
  }

  property("a > b") {
    forAll((a: ULong, b: ULong) => a > b == a.toBigInt > b.toBigInt)
  }

  property("a >= b") {
    forAll((a: ULong, b: ULong) => a >= b == a.toBigInt >= b.toBigInt)
  }

  test("max / min") {
    assertEquals(ULong.max, ULong(-1))
    assertEquals(ULong.min, ULong(0))
  }

  test("toLong") {
    assertEquals(ULong.max.toLong, -1L)
    assertEquals(ULong.min.toLong, 0L)
  }

  test("toBigInt") {
    assertEquals(ULong.max.toBigInt, BigInt("18446744073709551615"))
    assertEquals(ULong.min.toBigInt, BigInt(0))
  }

  property("n.toBigInt == BigInt(Long.toUnsignedString(n.toLong))") {
    forAll((n: ULong) => n.toBigInt == BigInt(JLong.toUnsignedString(n.toLong)))
  }

  property("n.render") {
    forAll((n: ULong) => n.render == n.toBigInt.toString)
  }

  property("n.toString = n.toBigInt.toString") {
    forAll((n: ULong) => n.toString == n.toBigInt.toString)
  }

  checkAll("Order[ULong]", OrderTests[ULong].order)

}
