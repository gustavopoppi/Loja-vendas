window.addEventListener("load", onLoad);

function onLoad() {
    updateBsTarget();
}

function updateBsTarget() {
    let rowsSizeCostumerTable = document.querySelector("#customerTable").tBodies[0].children.length

    for (let index = 1; index <= rowsSizeCostumerTable; index++) {
        document.querySelector("#triggerModal"+index).dataset.bsTarget = "#modalDelete"+index;
    }
}