(ns echo.representation-test
  (:require [clojure.test :refer :all]
            [echo.representation :refer :all]
            [echo.core :refer :all]
            [clojure.pprint :refer [pprint]]
            [clojure.java.io :refer [as-file]]))

(deftest edn-representation-test
  (testing "it works"
    (let [data {:foo {:request  {:foo "bar"}
                      :response {:bar "baz"}}}]
      (reset! requests data)
      (is (= (with-out-str
               (pprint data))
             (edn-representation))))))

(deftest edn-file-representations-test
  (testing "it creates files"
    (let [data {:foo {:request {:foo "bar"}
                      :response {:foo "bar"}}}]
      (reset! requests data)
      (edn-file-representations "requests")
      (is (.exists (as-file "requests/foo.request"))))))
