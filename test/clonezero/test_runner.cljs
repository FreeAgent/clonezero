(ns clonezero.test-runner
 (:require [doo.runner :refer-macros [doo-tests]]
           [clonezero.core-test]
           [cljs.nodejs :as nodejs]))

(try
  (.install (nodejs/require "source-map-support"))
  (catch :default _))

(doo-tests
 'clonezero.core-test)
