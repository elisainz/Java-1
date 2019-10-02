$(function(){
    var listGames = $("ol")
    var games = $.getJSON({
        url:"http://localhost:8080/api/games",
        })
        .done(function ()
            {
            data.map(function()
                {
                let date = new Date(data.created).toLocaleString()

                '<li>' +  + '</li>'
                )})
            }
            )}
