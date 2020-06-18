package edu.holycross.shot.mid.markupreader
import org.scalatest.FlatSpec

import edu.holycross.shot.cite._
import edu.holycross.shot.ohco2._

class MidProseABDiplomaticSpec extends FlatSpec {



  "The MidProseABDiplomatic object" should "create diplomatic CEX from a parsed node" in {
    val xml = "<div n=\"1\"><ab>Text 1<del>.1</del><add>.2</add> version</ab></div>"
    val urn =CtsUrn("urn:cts:mid:unittests.1.xml:1")
    val actual = MidProseABDiplomatic.editedNodeCex(urn, xml, MidDiplomaticEdition)
    val expected = "urn:cts:mid:unittests.1.xml:1#Text 1 .1 version"
    assert (actual.trim == expected)
  }

  it should "compose CEX for a node" in {
    val srcCex = "urn:cts:tests:dummy.txt1.v1:1#<div n=\"1\"><ab>Text 1<del>.1</del><add>.2</add> version</ab></div>"
    val expected = "urn:cts:tests:dummy.txt1.v1:1#Text 1 .1 version"
    assert(MidProseABDiplomatic.editedNodeCex(srcCex, MidDiplomaticEdition).trim == expected)
  }
}
