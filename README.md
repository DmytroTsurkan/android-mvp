Android MVP Practice Project
============================

## Why

I wanted to see how MVP would work when you bring in RecyclerViews and custom views, so I made this project.

There are 3 activities, a simple login activity, a list activity, and a details activity.

## How

Each Activity implements a `PresenterView` subinterface, this interface describes methods that change the view, such as `showLoadingIcon()` or `hideLoadingIcon()` or `loadResultsIntoRecyclerView()`

Each Activity also has a `Presenter` subclass which is relevant from `onCreate` to `onDestroy`.

On the Activities' various events, `onSubmitClick()`, `onStart()`, ...any event, `presenter.event()` is called where presenter will perform logic and maybe update the view with calls like `view.showLoadingIcon()`, `view.hideLoadingIcon()`, `view.loadItemsIntoRecyclerView()`

## Where

Take a look at the android tests.

It is crazy awesome how easy it becomes to test that certain actions happen following an event.

### NOTE
Since this project uses RxJava, Retrofit2, Dagger2, there are ways of using a MockService to mock network calls.

Nonetheless, network calls are still asynchronous, so in our tests we need to account for that. which requires some hackery in the wiring of the tests