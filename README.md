# echo

A Clojure library designed to for recording HTTP requests performed by your tests for use in API documentation.

![Clojars Project](http://clojars.org/ashtonkemerling/echo/latest-version.svg)

## Usage

Currently Echo is very small. A few important functions are provided inside of echo.core and echo.storage:

Use the following fixtures to store your example requests.

```clojure
(use-fixtures :once (fn [f] 
                      (f)
                      (store-edn "requests")))
```

And your tests should look like so:

```clojure
(deftest recording-test
  (testing "Records changes with canonical-example"
    (canonical-example-for "foo"                           
      (is (= (echo identity (request :get "/foo/bar")))
              ....))))
```           

This will store the request and response for your test in ```requests/foo.request```, which you can parse later for usage in your documentation.

## License

Copyright © 2014 Ashton Kemerling

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
