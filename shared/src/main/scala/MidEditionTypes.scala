package edu.holycross.shot.mid.markupreader


import scala.scalajs.js.annotation._


// Implementations of the [[MidEditionType]] trait.
/**  */
@JSExportTopLevel("MidDiplomaticEdition") case object MidDiplomaticEdition extends MidEditionType {
  def label =  "diplomatic"
  def description = "Pure diplomatic reading"
  def versionExtension = ""
}

@JSExportTopLevel("MidScribalEdition") case object MidScribalEdition extends MidEditionType {
  def label =  "scribal"
  def description = "Edition incorporating scribal corrections and expanding abbreviations"
  def versionExtension = "_scribal"
}

@JSExportTopLevel("MidNormalizedEdition") case object MidEditorialEdition extends MidEditionType {
  def label =  "editorial"
  def description = "Edition incorporating scribal corrections and expanding abbreviations, and modern editorial normalizations"
  def versionExtension = "_editorial"
}
