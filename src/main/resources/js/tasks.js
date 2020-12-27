tasksURL="http://localhost:9090/tasks/";

new Vue({
    el:"#tasks",
    data() {
        return {
            tasks: []
        }
    },
    created() {
        fetch(tasksURL)
            .then(response => {
                return response.json();
            })
            .then(tasks => {
                this.tasks = tasks;
            })
    }
})