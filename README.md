# PDF Validator Services REST
![CD](https://github.com/vetler/pdfvalidator-services-rest/workflows/Continous%20deployment%20of%20master/badge.svg)

## How to use

Validate PDF files for PDF/A compliance with a simple REST API.
It can be used to validate a file like this:

    curl -v -X POST -F 'file=@foo.pdf' http://localhost:8080/validate  
    
If the validation was a success, the service will return JSON like this:

    {
        "flavour": "PDFA_1_B",
        "result": "valid"
    }

If it was unvalid or unable to complete the validation, it will either return
result `invalid` or `unknown`.

If you want to validate the PDF against a specific PDF/A flavour, it can be
specified with the `flavour` parameter:

    curl -v -X POST -F 'flavour=1a' -F 'file=@fail.pdf' http://localhost:8080/validate
    
    {
        "errors": [
            "ISO 19005-1:2005, 6.8.3, test no. 1: The logical structure of the conforming file shall be described by a structure hierarchy rooted in the StructTreeRoot entry of the document catalog dictionary, as described in PDF Reference 9.6",
            "ISO 19005-1:2005, 6.8.2, test no. 1: The document catalog dictionary shall include a MarkInfo dictionary with a Marked entry in it, whose value shall be true.",
            "ISO 19005-1:2005, 6.7.11, test no. 3: A Level A conforming file shall specify the value of pdfaid:conformance as A."
        ],
        "flavour": "PDFA_1_A",
        "result": "invalid"
    }

### License
All source code licensed under the [Apache 2.0 license](http://www.apache.org/licenses/LICENSE-2.0).
Some test files © 2015 [veraPDF Consortium](http://www.verapdf.org "veraPDF Project home page"), licensed under [Creative Commons Attribution 4.0 International (CC BY 4.0)](https://creativecommons.org/licenses/by/4.0/)
