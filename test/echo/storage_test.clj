(ns echo.storage-test
  (:require [clojure.test :refer :all]
            [echo.storage :refer :all]
            [echo.core :refer :all]
            [clojure.pprint :refer [pprint]]
            [clojure.java.io :refer [as-file]]))


(deftest store-edn-test
  (testing "it creates files with the expected-data"
    (let [data {:foo {:request {:foo "bar"}
                      :response {:foo "bar"}}}]
      (reset! requests data)
      (store-edn "requests")
      (is (.exists (as-file "requests/foo.request")))
      (is (= (:foo data)
             (read-string (slurp "requests/foo.request")))))))
