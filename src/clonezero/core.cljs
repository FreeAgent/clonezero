(ns clonezero.core
  (:require [cljs-lambda.util :as lambda]
            [cljs-lambda.context :as ctx]
            [cljs-lambda.macros :refer-macros [deflambda]]
            [cljs.reader :refer [read-string]]
            [cljs.nodejs :as nodejs]
            [cljs.core.async :as async]
            [promesa.core :as p])
  (:require-macros [cljs.core.async.macros :refer [go]]))

(def config
  (-> (nodejs/require "fs")
      (.readFileSync "static/config.edn" "UTF-8")
      read-string))

(deflambda work-magic [{:keys [magic-word x y] :as input} ctx]
  (when (not= magic-word (config :magic-word))
    (throw (js/Error. "Your magic word is garbage")))
  (if (= (input :spell) "adder")
    (+ x y)
    (ctx/environment ctx)))
    ; (cast-async-spell input ctx)))
