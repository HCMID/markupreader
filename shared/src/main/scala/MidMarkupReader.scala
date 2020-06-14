package edu.holycross.shot.mid.markupreader
import edu.holycross.shot.cite._
import edu.holycross.shot.ohco2._

import scala.scalajs.js.annotation._

/** A class capable of reading marked up archival
* editions, and creating editions of specified types.
*/
trait MidMarkupReader {

  /** Vector of all recognized editionTypes. */
  def recognizedTypes: Vector[MidEditionType]

  /** Specific edition type to apply.
  def editionType: MidEditionType
*/

  /**  Given a  citable node in archival
  * format, compose the CEX String for the corresponding
  * node in the edition type specified by [[editionType]].
  *
  * @param cexNode CEX String for a single node in
  * archival format.
  */
    @JSExport def editedNodeCex(cn: CitableNode,
      editionType: MidEditionType,  separator: String = "#"):  String = {
    val editionNode = editedNode(cn, editionType)
    editionNode.urn.toString + separator + editionNode.text
  }

  /**  Given a  citable node in archival
  * format, create the corresponding
  * node in the edition type specified by [[editionType]].
  *
  * @param cn A single node in archival format.
  */
  def editedNode(cn: CitableNode,
  editionType: MidEditionType): CitableNode


  /**  For a citable node in archival format, compose
  * the CEX String for the corresponding node in
  * the edition type specified by [[editionType]].
  *
  * @param archival Text contents of a single citable node,
  * in archival representation.
  * @param srcUrn `CtsUrn` for the citable node.
  */
  @JSExport def editedNodeCex(srcUrn: CtsUrn, archival: String,
  editionType: MidEditionType): String = {
    editedNodeCex(CitableNode(srcUrn, archival), editionType)
  }

  /**  For a  citable node in archival
  * format, create the corresponding
  * node in the edition type specified by [[editionType]].
  *
  * @param archival Text contents of a single citable node,
  * in archival representation.
  * @param srcUrn `CtsUrn` for the citable node.
  */
  @JSExport def editedNode(srcUrn: CtsUrn, archival: String, editionType: MidEditionType): CitableNode = {
    editedNode(CitableNode(srcUrn, archival), editionType: MidEditionType)
  }

  /**  Given a CEX String for a citable node in archival
  * format, compose the CEX String for the corresponding
  * node in the edition type specified by [[editionType]].
  *
  * @param cexNode CEX String for a single node in
  * archival format.
  */
  @JSExport def editedNodeCex(cexNode: String, editionType: MidEditionType):  String = {
    val cols = cexNode.split("#")
    val urn = CtsUrn(cols(0))
    editedNodeCex(urn, cols(1), editionType: MidEditionType)
  }

  /**  For a  citable node in archival
  * format, create the corresponding
  * node in the edition type specified by [[editionType]].
  *
  * @param cexNode CEX String for a single node in
  * archival format.
  */
  @JSExport def editedNode(cexNode: String, editionType: MidEditionType): CitableNode = {
    val cols = cexNode.split("#")
    val urn = CtsUrn(cols(0))
    editedNode(urn, cols(1), editionType: MidEditionType)
  }




  /** Given a CEX String with text contents in
  * archival format, create a CEX String with
  * edition of type [[editionType]].
  *
  * @param cex CEX String of text(s) in archival
  * format.
  */
  @JSExport def editionCex(cex: String, editionType: MidEditionType): String = {
    val cexNodes = for (ln <- cex.split("\n")) yield {
      editedNodeCex(ln, editionType: MidEditionType)
    }
    cexNodes.mkString("\n")
  }


  /** Given a corpus in archival format, create a new Corpus
  * of the type specified for this reader.
  *
  * @param corpus A Corpus in archival format.
  */
  @JSExport def edition(corpus: Corpus, editionType: MidEditionType) : Corpus = {
    val nodes = for (n <- corpus.nodes) yield {
      editedNode(n, editionType: MidEditionType)
    }
    Corpus(nodes.toVector)
  }

}
