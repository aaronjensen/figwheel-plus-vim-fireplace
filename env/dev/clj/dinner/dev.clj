(ns dinner.dev
  (:require
    [figwheel-sidecar.auto-builder :as fig-auto]
    [figwheel-sidecar.core :as fig]))

(defn start-figwheel []
  (let [server (fig/start-server { :css-dirs ["resources/public/css"] })
        config {:builds [{:id "dev"
                          :source-paths ["env/dev/cljs" "src/cljs"]
                          :compiler {:main                 "dinner.dev"
                                     :output-to            "resources/public/js/app.js"
                                     :output-dir           "resources/public/js/out"
                                     :asset-path           "js/out"
                                     :optimizations        :none
                                     :pretty-print         true
                                     :source-map           true}}]
                :figwheel-server server}]
    (fig-auto/autobuild* config)))
