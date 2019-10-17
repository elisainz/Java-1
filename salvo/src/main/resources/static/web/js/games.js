$(function() {
    loadDataLeaderboard();
});



function updateView(data) {
    let htmlList = data.map(function (games) {
        return  '<li>' + new Date(games.created).toLocaleString() + ' ' + games.////???? .map(function(p) { return p.player.email}).join(',')  +'</li>';
    }).join('');
  document.getElementById("game-list").innerHTML = htmlList;
}

// load and display JSON sent by server for /players

function loadDataLeaderboard (){
    $.get("/api/leaderboard")
        .done(function(data) {
           updateView(data);
         })
         .fail(function( jqXHR, textStatus ) {
           alert( "Failed: " + textStatus );
          });
}