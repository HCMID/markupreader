---
title: The MidProseABReader
layout: page
---

**Library version @VERSION@**

## Example implementation

The `MidProseABDiplomatic` is a markup reader for prose texts in TEI-conformant XML documents with canonical citation values on `div`s and text content in `ab` elements.  It parses the XML into a diplomatic edition.

Let's import the library and see what it can do.

```scala mdoc
import edu.holycross.shot.mid.markupreader._
```

Markup readers can identify all the edition types they are capable of creating from the type of document they recognize.  From a single XML source document, for example, a reader might generate either a diplomatic or normalized edition.

Our simple example can generate only one type of edition, an `MidDiplomaticEdition`.


```scala mdoc
MidProseABDiplomatic.recognizedTypes
```

Its core functionality is to take a citable node in the format it understands and generate a new, edited node.


Let's create a `CitableNode` that includes both an addition and a deletion tagged with TEI `add` and `del` elements.

```scala mdoc:silent
import edu.holycross.shot.cite._
import edu.holycross.shot.ohco2._

val xml = "<div n=\"1\"><ab>Text in <del>first</del><add>second</add> version</ab></div>"
val urn = CtsUrn("urn:cts:mid:readerguide.xml.v1:1")
val citableNode = CitableNode(urn, xml)
```

We create a new `CitableNode` with the `editedNode` method. Its two parameters are the source node and type of edition to produce.  Since we previously saw that the `MidProseABDiplomatic` only knows how to produce `MidDiplomaticEdition`s, that will be the only valid value for the second parameter.

```scala mdoc
MidProseABDiplomatic.editedNode(citableNode, MidDiplomaticEdition)
```

The resulting `CitableNode` has a plain-text edition with diplomatic content.  Its URN identifies it as the same passage in the same work, but with a different version identifer (here, `v1_dipl`).



Since the markup reader can convert a `CitableNode` into a new `CitableNode`, it can necessarily convert an OHCO2 `Corpus` into a new `Corpus`.  We can illustrate that with a very small corpus.


```scala mdoc:silent
val xml2 = "<div n=\"2\"><ab>Section 2 of text.</ab></div>"
val urn2 = CtsUrn("urn:cts:mid:readerguide.xml.v1:2")
val citableNode2 = CitableNode(urn2, xml2)

val corpus = Corpus(Vector(citableNode, citableNode2))
```

Parallel to the `editedNode` method, the `edition` method takes two parameters:  the source corpus and the type of edition to create.
```scala mdoc
MidProseABDiplomatic.edition(corpus, MidDiplomaticEdition)
```

Markup readers include a number of convenience methods for accepting input or producing output in different forms, for example:

```scala mdoc
MidProseABDiplomatic.editedNodeCex(citableNode, MidDiplomaticEdition)
```

Refer to the API documenation for full details.
