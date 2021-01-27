tasksURL="http://localhost:9090/tasks/";

Vue.component('task-info',{

    props: ['task'],

    methods:{
        putNewTask(){
            this.task.status = 'PERFORMED';
            this.updateTask()
        },

        putPerformedTask(){
            this.task.status = 'COMPLETED';
            this.updateTask()
        },

        updateTask() {
            fetch("http://localhost:9090/tasks/" + this.task.id, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(this.task)
            });
        }
    },

    template: '<div v-if="task!=\'\'">' +
                    '<p>{{task.name}}</p>' +
                    '<p>{{task.text}}</p>' +
                    '<p> Прикрепленные файлы:</p>'+
                    '<form action="tasksURL" v-if="task.status ===\'NEW\'">' +
                            '<input type="button" value="Взять в работу" @click="putNewTask"/></form>' +
                    '<form action="tasksURL" v-if="task.status ===\'PERFORMED\'">' +
                            '<input type="button" value="Завершить" @click="putPerformedTask"/></form>' +
              '</div>'

})


vmTasks = new Vue({
    el:"#tasks",
    data() {
        return {
            tasks: [],
            selectedTask: '',
            newTask: ''
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

