function showMyTeam(){

}
function onClickDeleteUserFromTeam() {
    const url = "http://localhost:8080/Site/team";
    var email = document.getElementById("email_to_delete").value;
    var userObj = {
        'email':email
    };
    let response = fetch(url, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Action': 'Delete'
        },
        body: JSON.stringify(userObj)
    });
    let result = response.text();


}

function onClickToAddUserToTeam(){
    const url = "http://localhost:8080/Site/team";
    var email = document.getElementById("email_to_add").value;
    var userObj = {
        'email':email
    };
    let response = fetch(url, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Action': 'Add'
        },
        body: JSON.stringify(userObj)
    });
    let result = response.text();
}
function onClickShowTeam(){
    var team = document.getElementById("team_name").value;
    const url = "http://localhost:8080/Site/myTeams";
    var userObj = {
        'name':team
    };
    let response = fetch(url, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(userObj)
    });
    let result = response.text();

}