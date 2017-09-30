module easytext.analysis.coleman {
    requires easytext.analysis.api;

    provides javamodularity.easytext.analysis.api.TextAnalyzer
        with javamodularity.easytext.analysis.coleman.ColemanLiauProviderFactory;
}