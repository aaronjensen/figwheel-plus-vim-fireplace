This serves as a reproduction for https://github.com/Day8/re-frame/issues/60

To repro, run 1 & 2 below then check

https://github.com/aaronjensen/figwheel-plus-vim-fireplace/blob/37c70a7d92c703b30158ee130fc0e4ceba77cf31/src/cljs/dinner/subs.cljs#L11-14
and
https://github.com/aaronjensen/figwheel-plus-vim-fireplace/blob/37c70a7d92c703b30158ee130fc0e4ceba77cf31/src/cljs/dinner/core.cljs#L22-24

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
