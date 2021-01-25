tasksURL="http://localhost:9090/tasks/";

Vue.component('task-info',{

    props: ['task'],

    template: '<div v-if="task!=\'\'">' +
                    '<p>{{task.name}}</p>' +
                    '<p>{{task.text}}</p>' +
                    '<p> Прикрепленные файлы:</p>'+
                    '<form v-if="task.status ===\'NEW\'"><input type="button" value="Взять в работу"/></form>' +
                    '<form v-if="task.status ===\'PERFORMED\'"><input type="button" value="Завершить"/></form>' +
              '</div>'

})


vmTasks = new Vue({
    el:"#tasks",
    data() {
        return {
            tasks: [],
            selectedTask: ''
        }
    },

    methods:{
        selectTask(task){
            this.selectedTask = task;
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

