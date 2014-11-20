(ns echo.representation
  (:require [echo.core :refer [requests]]
            [clojure.pprint :refer [pprint]]))

(defn edn-representation []
  (with-out-str
    (pprint @requests)))
