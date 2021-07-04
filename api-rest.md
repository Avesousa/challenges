- [Introduction](#introduction)
- [Requirement](#requirement)
- [Notes](#notes)
- [Expectations](#expectations)
- [Problem Statement](#problem-statement)

## Introduction

As a software engineer, you have to provide a reliable system to clients.
Your first task here is to implement an endpoint using **Spotify** api.

## Requirement

1. We value a **clean**, **simple** working solution.
2. The solution must be production ready.
3. Good understanding for how git works.
4. Good understanding of **REST API's**.

## Notes

- Source code must be pushed as git branch in the provided project repository.
- Your branch name should follow this scheme `challenge/lastname-firstname`.
- (Optional) Deploy as a public api to your own host.

## Expectations

- This challenge should take around 6 to 8 hours to complete.
- Your code should be modular, each module should focus on doing one thing and do it well.
- Avoid over-engineering.

## Problem Statement

Using the spotify api create an endpoint to which entering the name of the band will obtain an array of the entire discography, each disk must have this format:
```
[{
    "name": "Album Name",
    "released": "10-10-2010",
     "tracks": 10,
     "cover": {
         "height": 640,
         "width": 640,
         "url": "https://i.scdn.co/image/6c951f3f334e05ffa"
     }
 },
  ...
]
```

The endpoint must be the following
```
http://localhost/api/v1/albums?q=<band-name>
```

- You can use any framework (or micro framework)
- You can use libs
- No spotify SDK can be used.

**[⬆ back to top](#introduction)**
