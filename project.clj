(defproject dinner "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}

  :source-paths ["src/clj" "src/cljs"]
  :repositories {"local" "file:maven_repository"}

  :dependencies [[org.clojure/clojure "1.6.0"]
                 [ring-server "0.4.0"]
                 [cljsjs/react "0.13.1-0"]
                 [reagent "0.5.0"]
                 [reagent-forms "0.5.0"]
                 [reagent-utils "0.1.4"]
                 [ring "1.3.2"]
                 [ring/ring-defaults "0.1.4"]
                 [prone "0.8.1"]
                 [compojure "1.3.3"]
                 [hiccup "1.0.5"]
                 [environ "1.0.0"]
                 [org.clojure/clojurescript "0.0-3211" :scope "provided"]
                 [secretary "1.2.3"]
                 [prismatic/schema "0.4.2"]
                 [re-frame "0.4.0"]]

  :plugins [[lein-ring "0.9.1"]
            [lein-environ "1.0.0"]
            [lein-asset-minifier "0.2.2"]]

  :ring {:handler dinner.handler/app
         :uberwar-name "dinner.war"}

  :min-lein-version "2.5.0"

  :uberjar-name "dinner.jar"

  :main dinner.server

  :clean-targets ^{:protect false} ["resources/public/js"]

  :minify-assets
  {:assets
    {"resources/public/css/site.min.css" "resources/public/css/site.css"}}

  :cljsbuild {:builds {:app {:source-paths ["src/cljs"]
                             :compiler {:output-to     "resources/public/js/app.js"
                                        :output-dir    "resources/public/js/out"
                                        :asset-path   "js/out"
                                        :optimizations :none
                                        :pretty-print  true}}}}

  :profiles {:dev {:repl-options {:init-ns dinner.repl
                                  :nrepl-middleware [cemerick.piggieback/wrap-cljs-repl]}

                   :dependencies [[ring-mock "0.1.5"]
                                  [ring/ring-devel "1.3.2"]
                                  [weasel "0.7.0-SNAPSHOT"]
                                  [leiningen-core "2.5.1"]
                                  [com.cemerick/piggieback "0.2.1"]
                                  [org.clojure/tools.nrepl "0.2.10"]
                                  [figwheel-sidecar "0.3.2"]
                                  [pjstadig/humane-test-output "0.7.0"]]

                   :source-paths ["env/dev/clj"]
                   :plugins [[lein-figwheel "0.3.2"]
                             [lein-cljsbuild "1.0.5"]
                             [com.cemerick/clojurescript.test "0.3.2"]]

                   :injections [(require 'pjstadig.humane-test-output)
                                (pjstadig.humane-test-output/activate!)]

                   :figwheel {:http-server-root "public"
                              :server-port 3449
                              :nrepl-port 7888
                              :css-dirs ["resources/public/css"]
                              :ring-handler dinner.handler/app}

                   :env {:dev true}

                   :cljsbuild {:builds {:app {:source-paths ["env/dev/cljs"]
                                              :compiler {:main "dinner.dev"
                                                         :source-map true}}
                                        :test {:source-paths ["src/cljs"  "test/cljs"]
                                               :compiler {:output-to "target/test.js"
                                                          :optimizations :whitespace
                                                          :pretty-print true
                                                          :preamble ["react/react.js"]}}}
                               :test-commands {"unit" ["phantomjs" :runner
                                                       "test/vendor/es5-shim.js"
                                                       "test/vendor/es5-sham.js"
                                                       "test/vendor/console-polyfill.js"
                                                       "target/test.js"]}}}

             :uberjar {:hooks [leiningen.cljsbuild minify-assets.plugin/hooks]
                       :env {:production true}
                       :aot :all
                       :omit-source true
                       :cljsbuild {:jar true
                                   :builds {:app
                                             {:source-paths ["env/prod/cljs"]
                                              :compiler
                                              {:optimizations :advanced
                                               :pretty-print false}}}}}})
