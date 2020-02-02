<template>
  <div class="home-container">
    <div class="column-container">
      <div class="column" id="todo" @dragover="onDragOver($event)" @drop="onDrop($event)">
        <div class="title-container">
          <img src="/img/icons/list-ul-solid.svg" class="column-icon"> TODO
        </div>
        <KanbanCard
          v-for="task in tasklist"
          :key="task.id"
          :id="task.id"
          draggable="true"
          @dragstart="onDragStart($event)"
          @dragend="onDragEnd($event)"
          :task="task"
        />
      </div>
      <div class="column" id="ongoing" @dragover="onDragOver($event)" @drop="onDrop($event)">
        <div class="title-container">
          <img src="/img/icons/spinner-solid.svg" class="column-icon"> ONGOING
        </div>
        <KanbanCard
          v-for="task in ongoingTaskList"
          :key="task.id"
          :id="task.id"
          draggable="true"
          @dragstart="onDragStart($event)"
          @dragend="onDragEnd($event)"
          :task="task"
        />
      </div>
      <div class="column" id="done" @dragover="onDragOver($event)" @drop="onDrop($event)">
        <div class="title-container">
          <img src="/img/icons/check-circle-solid.svg" class="column-icon"> DONE
        </div>
        <KanbanCard
          v-for="task in doneTaskList"
          :key="task.id"
          :id="task.id"
          draggable="true"
          @dragstart="onDragStart($event)"
          @dragend="onDragEnd($event)"
          :task="task"
        />
      </div>
    </div>

    <AddTaskButton @click="toggleModal()"/>
    <CreateTaskModal v-if="showModal" @click="toggleModal()" @createTask="createTask"/>
  </div>
</template>

<script>
// @ is an alias to /src
import KanbanCard from "@/components/KanbanCard.vue";
import CreateTaskModal from "@/components/CreateTaskModal.vue";
import AddTaskButton from "@/components/AddTaskButton";
import axios from "axios";

export default {
  name: "home",
  components: {
    KanbanCard,
    AddTaskButton,
    CreateTaskModal
  },
  data: function() {
    return {
      showModal: false,
      tasklist: [],
      ongoingTaskList: [],
      doneTaskList: []
    };
  },
  created: function() {
    // `this` points to the vm instance
    var self = this;
    axios
      .get("http://localhost:8000/api/v1/task")
      .then(function(response) {
        response.data.map(task => {
          if (task.status == 0) self.tasklist.push(task);
          else if (task.status == 1) self.ongoingTaskList.push(task);
          else self.doneTaskList.push(task);
          return 1;
        });
      })
      .catch(function(error) {
        //console.log(error);
      });
  },
  methods: {
    toggleModal() {
      this.showModal = !this.showModal;
    },
    createTask(e, task) {
      e.preventDefault();
      axios
        .post("http://localhost:8000/api/v1/task", task)
        .then(response => {
          this.tasklist.push(response.data);
          this.toggleModal();
        })
        .catch(function(error) {
          //console.log(error);
        });
    },
    onDragStart(event) {
      event.dataTransfer.setData("text/plain", event.target.id);
    },
    onDragOver(event) {
      event.preventDefault();
    },
    onDragEnd(event) {
      const draggableElement = event.srcElement;
    },

    updateCard(movedElementData, tasklist) {
      axios
        .put(
          "http://localhost:8000/api/v1/task/" + movedElementData.id,
          movedElementData
        )
        .then(response => {
          tasklist.push(response.data);
        })
        .catch(function(error) {
          //console.log(error);
        });
    },
    onDrop(event) {
      const id = event.dataTransfer.getData("text");

      const draggableElement = document.getElementById(id);
      const dropzone = event.target;

      let movedElementData = this.tasklist.find(
        x => x.id == draggableElement.id
      );

      if (movedElementData == undefined) {
        movedElementData = this.ongoingTaskList.find(
          x => x.id == draggableElement.id
        );
        this.ongoingTaskList = this.ongoingTaskList.filter(
          x => x.id != draggableElement.id
        );
      } else {
        this.tasklist = this.tasklist.filter(x => x.id != draggableElement.id);
      }
      if (movedElementData == undefined) {
        movedElementData = this.doneTaskList.find(
          x => x.id == draggableElement.id
        );
        this.doneTaskList = this.doneTaskList.filter(
          x => x.id != draggableElement.id
        );
      }
      let taskStatus = -1;
      if (event.target.parentNode.className == "card-container") {
        event.target.parentNode.before(draggableElement);
        taskStatus = event.target.parentNode.parentNode.id;
      } else if (event.target.parentNode.className == "column") {
        event.target.before(draggableElement);
        taskStatus = event.target.parentNode.id;
      } else if (event.target !== event.currentTarget) return;
      else {
        dropzone.appendChild(draggableElement);
        taskStatus = dropzone.id;
      }

      if (taskStatus != -1) {
        if (taskStatus == "todo") {
          movedElementData.status = 0;
          this.updateCard(movedElementData, this.tasklist);
        } else if (taskStatus == "ongoing") {
          movedElementData.status = 1;
          this.updateCard(movedElementData, this.ongoingTaskList);
        } else if (taskStatus == "done") {
          movedElementData.status = 2;
          this.updateCard(movedElementData, this.doneTaskList);
        }
      }

      event.dataTransfer.clearData();
    }
  }
};
</script>

<style scoped>
.home-container {
  height: 100%;
}
.column-container {
  margin: 0 auto;
  display: flex;
  width: 1200px;
  justify-content: center;
  height: 100%;
}
.column {
  float: left;
  padding-right: 50px;
  height: 100%;
  width: 33%;
}
.column-icon {
  height: 30px;
  width: 30px;
  margin-top: 3px;
  margin-right: 8px;
}

.title-container {
  font-size: 32px;
  margin: 10px 0px 20px 15px;
  text-align: left;
  display: flex;
  color: #444243;
}
</style>
