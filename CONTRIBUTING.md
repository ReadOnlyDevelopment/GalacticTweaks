
# How to contribute

We want to keep it as easy as possible to contribute changes. 
There are a few guidelines that we need contributors to follow 
so that we can keep on top of things.

## Getting Started

### Submitting Changes

* Submit an issue to the github project, assuming one does not already exist.
  * Clearly describe the issue including steps to reproduce when it is a bug.
  * Make sure you fill in the earliest version that you know has the issue.
  * Waiting for feedback is suggested.
* Fork the repository on GitHub
* Create a topic branch from where you want to base your work.
  * This `dev-1.12.2` branch that is under active development.
  * Only target the Release branch
  * To quickly create a topic branch based on the development branch; `git 
    checkout -b my_contribution_branch`. Please avoid working 
    directly on the `dev-1.12.2` branch.
* Make commits of logical units.
* Check for unnecessary whitespace with `git diff --check` before committing.
* Make sure your commit messages are in the proper format.

````
    (#12345) An Example Commit Message Adding An Example Feature/Fix

    If your commit requires additional information for the developers
    you can add it after the first line with a space.

    The first line must contain your issue/suggestion ticket #
````
* Always fully test your changes.
* Describing the process you used to test your changes in detail will help speed up this process.

## Making Trivial Changes

### Documentation

For changes of a trivial nature to comments and documentation, it is not always necessary to create a new issue. 
We usually use `squash and merge`.
````

### Semantic Changes

In order to keep the code in a state where PRs can be safely merged, it is important to
avoid changes to syntax or changes that don't add any real value to the code base. PRs
that make changes only to syntax or "clean up" the code will be rejected. Any code clean-up
should be coordinated with the core team first.

# Additional Resources

* [General GitHub documentation](http://help.github.com/)
* [GitHub pull request documentation](http://help.github.com/send-pull-requests/)
