module easytext.analysis.kincaid {
    requires easytext.analysis.api;

    provides javamodularity.easytext.analysis.api.TextAnalyzer
        with javamodularity.easytext.analysis.kincaid.FleshKincaid;
}