# Gitlet Design Document

**Name**: Chongfeng Ling

## Classes and Data Structures

### Main

Entry point to Gitlet. It takes in arguments from the command line and based on the command (the first element of the args array) calls the corresponding command in `Repository` which will actually execute the logic of the command. It also validates the arguments based on the command to ensure that enough arguments were passed in.

#### Fields

This class has no fields and hence no associated state. It simply validates arguments and defers the execution to the `Repository` class.

### Repository

This contains the main logic of the program. This file will handle all the actual gitlet commands by reading/writing from/to the correct file, setting up persistence and additional error checking.

It will also be responsible for setting up all persistence within gitlet, including creating the `.gitlet` folders as well as the folder and file where we store all objects (including `Commit`, `Blob` and `Stage`) and the current `HEAD` and branch.

This class defers all `Commit`, `Blob` and `Stage` objects specific logic to the corresponding class: for example, instead of having the `Repository` class handle `Commit` serialization and deserialization, we have the `Commit` class contain the logic for that.

#### Fields

1. static
2. static

These fields are both `static` since we don't actually instantiate a `Repository` object: we simply use it to house functions. If we had additional non-static state, we'd need to serialize it and save it to a file.

### Commit

This class represents a `Commit` that will be stored in a file. Because each `Commit` will have a unique sha1 code, we may simply use that as the name of the file that the object is serialized to.

All `Commit` objects are serialized within the `Objects` plus its first two sha1 code which is within the `.gitlet`. The `Commit` class has helpful methods that will return the `Commit` object corresponding to some String name given to it, as well as write that `Commit` to a file to persist its changes.

#### Fields

### Blob

This class represents a `Blob` that will be stored in a file. Because each `Blob` will have a unique sha1 code, we may simply use that as the name of the file that the object is serialized to.

All `Blob` objects are serialized within the `Blob` plus its first two sha1 code which is within the `.gitlet`. The `Blob` class has helpful methods that will return the `Commit` object corresponding to some String name given to it, as well as write that `Blob` to a file to persist its changes.

#### Fields

### Stage

This class represents a `Stage` that will be stored in a file.

A `Stage` object at a time is serialized within the `INDEX` which is within the `.gitlet`. The `Stage` class has helpful methods that will return the `Commit` object corresponding to some String name given to it, as well as write that `Stage` to a file to persist its changes.

#### Fields

### Utils

This class contains helpful utility methods to read/write objects or `String` contents from/to files, as well as reporting errors when they occur.

This is a staff-provided and PNH written class, so we leave the actual implementation as magic and simply read the helpful javadoc comments above each method to give us an idea of whether it’ll be useful for us.

#### Fields

Only some `private` fields to aid in the magic.

## Algorithms

## Persistence

