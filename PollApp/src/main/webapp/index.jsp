<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset =="UTF-8">
    <title> Hello World </title>

    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">




</head>
<body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>


<div class="container-fluid">
    ...
</div>

<form action="PollApp"  method="Get">

    Enter the User ID : <input type="text" name="UserID"> <br>
    <br>
    <label for="UserType">Choose a User type:</label>
    <br><br>

    <select name="UserType" id="UserType">
        <option value="RegularUser">RegularUser</option>
        <option value="PollManager">PollManager</option>
    </select>
    <br> <br>
    <label >Enter the following informations to create a POLL </label>
    <br>
    <br>
    Name of The Poll : <input type="text" name="PollName"> <br>
    <br>
    Question of the Poll : <input type="text" name="PollQuestion"> <br>
    <br>
    <label >Enter the choices for this  bellow and separate them by a comma , choice1,choice2,choice3 etc </label>
    <br>
    <br>
    Choices of the Poll : <input type="text" name="PollChoice"> <br>
    <br>
    <label >Enter the description of the choices bellow and separate them by a comma , description1,description2,description3 etc </label>
    <br>
    <br>
    Description of the Poll : <input type="text" name="Description"> <br>
    <br>
    Now Click in the following button to run the poll
    <%= "Hello World!" %>
    <button type="button">Click here to run the pull !</button>
    <br>
    <br>

    <input type="submit" >
</form>

<br> <br>

<div> Welcome to SOEN 387 Tutorial </div>
<div>
    Click here to go to <a href="PollApp"> Hello World </a>
</div>
</body>
