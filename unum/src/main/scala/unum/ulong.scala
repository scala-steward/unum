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

import java.lang.Long as JLong
import cats.Order

object ULong:

  val min: ULong  = ULong(0L)
  val zero: ULong = ULong(0L)
  val max: ULong  = ULong(-1L)

  def apply(n: Long): ULong = new ULong(n)

  //

  given orderForULong: Order[ULong] =
    Order.from[ULong]((x, y) => JLong.compareUnsigned(x.signed, y.signed))

  given Ordering[ULong] = orderForULong.toOrdering

  extension (u: ULong)
    def toLong: Long     = u.signed
    def render: String   = JLong.toUnsignedString(u.signed)
    def toBigInt: BigInt = BigInt(JLong.toUnsignedString(u.signed))

final class ULong(val signed: Long) extends AnyVal {
  override def toString: String = JLong.toUnsignedString(signed)
}
