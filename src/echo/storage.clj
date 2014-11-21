(ns echo.storage
  (:require [echo.core :refer [requests]]
            [clojure.java.io :refer [make-parents writer]]
            [clojure.pprint :refer [pprint]]))

(defn store-edn [prefix]
  (let [names (atom {})]
    (doall (map (fn [[sample-name values]]
                  (let [filename (str prefix "/" (name sample-name) ".edn")]
                    (swap! names assoc sample-name filename)
                    (make-parents filename)
                    (with-open [wrtr (writer filename)]
                      (.write wrtr (with-out-str (pprint values))))))
                @requests))
    (with-open [wrtr (writer (str prefix "/summary.edn"))]
      (.write wrtr (with-out-str (pprint @names))))))
