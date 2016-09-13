(defproject clonezero "0.1.0-SNAPSHOT"
  :description "FIXME"
  :url "http://please.FIXME"
  :dependencies [[org.clojure/clojure       "1.8.0"]
                 [org.clojure/clojurescript "1.8.34"]
                 [org.clojure/core.async    "0.2.374"]
                 [io.nervous/cljs-lambda    "0.3.2"]]
  :plugins [[lein-cljsbuild "1.1.3"]
            [lein-npm       "0.6.0"]
            [lein-doo       "0.1.7-SNAPSHOT"]
            [io.nervous/lein-cljs-lambda "0.6.1"]]
  :npm {:dependencies [[source-map-support "0.4.0"]]}
  :source-paths ["src"]
  :cljs-lambda
  {:env {:set     {"CLJS_LAMBDA_EXAMPLE" "Yes"}
         :capture #{"USER" #"^TEST_"}}
   :defaults      {:role "arn:aws:iam::337828558053:role/clonezero"}
   :resource-dirs ["static"]
   :functions
   [{:name   "work-magic"
     :invoke clonezero.core/work-magic}]}
  :cljsbuild
  {:builds [{:id "clonezero"
             :source-paths ["src"]
             :compiler {:output-to     "target/clonezero/clonezero.js"
                        :output-dir    "target/clonezero"
                        :source-map    "target/clonezero/clonezero.js.map"
                        :target        :nodejs
                        :language-in   :ecmascript5
                        :optimizations :advanced}}
            {:id "clonezero-test"
             :source-paths ["src" "test"]
             :compiler {:output-to     "target/clonezero-test/clonezero.js"
                        :output-dir    "target/clonezero-test"
                        :target        :nodejs
                        :language-in   :ecmascript5
                        :optimizations :none
                        :main          clonezero.test-runner}}]})
