(ns echo.representation-test
  (:require [clojure.test :refer :all]
            [echo.representation :refer :all]
            [echo.core :refer :all]
            [clojure.pprint :refer [pprint]]))

(deftest edn-representation-test
  (testing "it works"
    (let [data {:foo {:request  {:foo "bar"}
                      :response {:bar "baz"}}}]
      (reset! requests data)
      (is (= (with-out-str
               (pprint data))
             (edn-representation))))))
