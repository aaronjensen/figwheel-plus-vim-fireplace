This serves as a reproduction for https://github.com/Day8/re-frame/issues/60

It is also an example of how to get vim-fireplace working with figwheel.

## Getting Started

1. Run
    ```bash
    $ lein repl
    ```
1. In the repl:
    ```clojure
    dinner.repl=> (run)
    ```
1. In vim (note: you must refresh your browser after running while vim is hung):
    ```viml
    :Piggieback (dinner.repl/repl-env)
    ```
