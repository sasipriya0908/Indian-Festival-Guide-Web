function getFestival() {
    return document.getElementById("festival").value.trim();
}

function showResult(message) {
    document.getElementById("ajax-result").innerHTML = message;
}

document.getElementById("servletBtn").addEventListener("click", function () {
    var festival = getFestival();

    if (festival === "") {
        showResult("Please enter a festival name.");
        return;
    }

    var xhr = new XMLHttpRequest();

    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4) {
            if (xhr.status === 200) {
                showResult(xhr.responseText);
            } else {
                showResult("Error occurred while contacting FestivalServlet.");
            }
        }
    };

    xhr.open("GET", "FestivalServlet?festival=" + encodeURIComponent(festival), true);
    xhr.send();
});

document.getElementById("xmlBtn").addEventListener("click", function () {
    var festival = getFestival().toLowerCase();

    if (festival === "") {
        showResult("Please enter a festival name.");
        return;
    }

    var xhr = new XMLHttpRequest();

    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4) {
            if (xhr.status === 200) {
                var xml = xhr.responseXML;
                var nodes = xml.getElementsByTagName("festival");
                var found = false;
                var output = "";

                for (var i = 0; i < nodes.length; i++) {
                    var name = nodes[i].getElementsByTagName("name")[0].textContent;
                    if (name.toLowerCase() === festival) {
                        var type = nodes[i].getElementsByTagName("type")[0].textContent;
                        var region = nodes[i].getElementsByTagName("region")[0].textContent;
                        var description = nodes[i].getElementsByTagName("description")[0].textContent;
                        output = "<div class='ajax-card'><h3>" + name + "</h3>" +
                                 "<p><b>Type:</b> " + type + "</p>" +
                                 "<p><b>Region:</b> " + region + "</p>" +
                                 "<p><b>Description:</b> " + description + "</p></div>";
                        found = true;
                        break;
                    }
                }

                showResult(found ? output : "No festival found in XML data.");
            } else {
                showResult("Unable to load festival-data.xml.");
            }
        }
    };

    xhr.open("GET", "festival-data.xml", true);
    xhr.send();
});

document.getElementById("jspBtn").addEventListener("click", function () {
    var festival = getFestival();

    if (festival === "") {
        showResult("Please enter a festival name.");
        return;
    }

    var xhr = new XMLHttpRequest();

    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4) {
            if (xhr.status === 200) {
                showResult(xhr.responseText);
            } else {
                showResult("Unable to load JSP response.");
            }
        }
    };

    xhr.open("GET", "ajax-response.jsp?festival=" + encodeURIComponent(festival), true);
    xhr.send();
});
