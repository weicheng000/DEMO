<script>
import {ref, watch} from "vue";
import axios from "@/intercepter/axios"

const url = "/api/emps";
export default {
  name: "table.vue",
  setup() {
    /**
     * 全表綁定
     */
    const vtable = ref(null);
    /**
     * 表頭設定
     * @type {Ref<UnwrapRef<[{title: string, align: string, key: string},{title: string, align: string, key: string},{title: string, align: string, key: string},{title: string, align: string, key: string},{title: string, align: string, key: string},null,null,null]>>}
     */
    const headers = ref([
      {
        title: "No.",
        key: "id",
        align: 'end'
      },
      {
        title: "Name",
        key: "name",
        align: 'end'
      }, {
        title: "Role",
        key: "role",
        align: 'end'
      },
      {
        title: "Gender",
        key: "sex",
        align: 'end'
      },
      {
        title: "Create Time",
        key: "createTime",
        align: 'center'
      },
      {
        title: "Update Time",
        key: "updateTime",
        align: 'center'
      },
      {
        title: "Department",
        key: "deptId",
        align: 'center'
      },
      {
        title: 'Actions',
        key: 'actions',
        sortable: false
      }
    ]);

    /**
     * 表上方搜索欄模型綁定
     * @type {Ref<UnwrapRef<string>>}
     */
    const search = ref('');
    const loading = ref(false);
    const searchItems = ref(['name', 'role', 'sex', 'deptId']);
    const searchValue = ref('name');


    /**
     * 彈窗控件
     * @type {Ref<UnwrapRef<boolean>>}
     */
    const dialog = ref(false);
    watch(dialog, (newValue) => {
      if (newValue === false) {
        close();
      }
    });
    const dialogDelete = ref(false);
    watch(dialogDelete, (newValue) => {
      if (newValue === false) {
        closeDelete();
      }
    })

    /**
     * 新增 / 編輯 邏輯控件
     * @type {Ref<UnwrapRef<boolean>>}
     */
    const editEvents = ref(false);
    const emp = ref({
      id: null,
      name: '',
      role: '',
      sex: '',
      deptId: 1
    });
    const defaultEmp = ref({
      id: null,
      name: '',
      role: '',
      sex: '',
      deptId: 1
    })

    /**
     * 表內資料設定
     * @type {Ref<UnwrapRef<*[]>>}
     */
    const page = ref(1);
    const items = ref([]);
    const totalItems = ref(0);
    const itemsPerPage = ref(10);

    /**
     * 初始化表內資料
     * @returns {Promise<void>}
     */
    const initial = async ({page, itemsPerPage, sortBy, search}) => {
      loading.value = true;
      let urls = url + "?page=" + page + "&itemPerPage=" + itemsPerPage;
      if (search !== null && search !== undefined){
        urls += `&${searchValue.value}=${search}`
      }
      if (sortBy !== null) {
        console.log(sortBy);
      }

      await axios().get(urls).then(result => {
        if (result.data.code === 1) {
          items.value = result.data.data.result;
          totalItems.value = result.data.data.total;
          loading.value = false;
        }
      });
    }

    /**
     * 根據性別選擇樣式
     * @param sex 傳入的參數
     * @returns {string} 返回的顏色字串
     */
    const getColor = (sex) => {
      switch (sex) {
        case 0:
          return 'red';
        case 1:
          return 'blue';
        default:
          return 'purple';
      }
    };
    const changGenderDataDisplay = (sex) => {
      switch (sex) {
        case 0:
          return "Female";
        case 1:
          return "Male";
        case 2:
          return "Secret"
        default:
          return "";
      }
    };

    /**
     * 整理時間格式
     * @param backendDate 後端傳來的時間格式，Java - LocalDateTime
     * @returns {`${number}-${string}-${string} ${string}:${string}:${string}`} 回傳 年-月-日 時:分:秒
     */
    const frontendDate = (backendDate) => {
      const date = new Date(backendDate);
      const year = date.getFullYear();
      const month = String(date.getMonth() + 1).padStart(2, '0');
      const day = String(date.getDate()).padStart(2, '0');
      const hours = String(date.getHours()).padStart(2, '0');
      const minutes = String(date.getMinutes()).padStart(2, '0');
      const seconds = String(date.getSeconds()).padStart(2, '0');

      return `${year}-${month}-${day} ${hours}:${minutes}:${seconds}`;
    };
    /**
     * 新增資料
     */
    const close = () => {
      dialog.value = false;
      emp.value = Object.assign({}, defaultEmp.value);
      editEvents.value = false;
    };
    /**
     * 保存事件
     * @returns {Promise<void>}
     */
    const save = async () => {
      console.log(emp.value);
      try {
        const requestBody = emp.value;

        await axios().post(url, requestBody)

      } catch (error) {
        console.error(error);
      }
      await initial({page: vtable.value.page, itemsPerPage: vtable.value.itemsPerPage, sortBy: vtable.value.sortBy});
      close();
    }
    /**
     * 修改資料
     */
    const editItem = (item) => {
      editEvents.value = true;
      emp.value = Object.assign({}, item);
      dialog.value = true;
    }
    /**
     * 刪除資料
     * @param item
     */
    const deleteItem = (item) => {
      emp.value.id = item.id;
      emp.value.name = item.name;
      dialogDelete.value = true;
    }
    const closeDelete = () => {
      dialogDelete.value = false;
      emp.value.name = '';
      emp.value.id = null;
    }
    const deleteItemConfirm = async () => {
      // console.log(emp.value.id);
      try {
        let urls = url + "?id=" + emp.value.id;

        await axios().delete(urls);
      } catch (error) {
        console.log(error);
      }
      await initial({page: vtable.value.page, itemsPerPage: vtable.value.itemsPerPage, sortBy: vtable.value.sortBy});
      closeDelete();
    }

    return {
      vtable,
      headers,
      search,
      searchValue,
      searchItems,
      loading,
      dialog,
      dialogDelete,
      editEvents,
      deleteItem,
      closeDelete,
      deleteItemConfirm,
      emp,
      close,
      save,
      editItem,
      items,
      page,
      totalItems,
      itemsPerPage,
      initial,
      getColor,
      frontendDate,
      changGenderDataDisplay
    }
  }
}
</script>

<template>
  <v-data-table-server
    ref="vtable"
    :page.async="page"
    :headers="headers"
    :search="search"
    :items="items"
    :items-per-page="itemsPerPage"
    :items-length="totalItems"
    class="elevation-1"
    :loading="loading"
    loading-text="Data loading now... Please wait."
    @update:options="initial"
  >
    <!--  表頭功能列  -->
    <template v-slot:top>
      <!--   搜索欄   -->
      <v-text-field
        v-model="search"
        clearable="true"
        label="Search"
      >
        <!--    搜索欄下拉選單    -->
        <template v-slot:prepend>
          <v-select
            label="Select Param"
            v-model="searchValue"
            variant="solo"
            hint="Pick Param"
            :items="searchItems"
          >
            <template v-slot:selection="{ item }">
              <v-chip color="indigo">
                <span v-if="item.title === 'name'">Name</span>
                <span v-else-if="item.title === 'role'">Role</span>
                <span v-else-if="item.title === 'sex'">Gender</span>
                <span v-else>Department</span>
              </v-chip>
            </template>
          </v-select>
        </template>
        <!--    搜索欄 icon    -->
        <template v-slot:prepend-inner>
          <v-fade-transition leave-absolute>
            <v-progress-circular
              v-if="loading"
              color="info"
              indeterminate
              size="24"
            ></v-progress-circular>

            <v-icon
              v-else
              icon="mdi-magnify"
            ></v-icon>
          </v-fade-transition>
        </template>
      </v-text-field>

      <!--  標題與按鈕   -->
      <v-toolbar flat="true">
        <v-toolbar-title>My CRUD</v-toolbar-title>

        <v-divider
          class="mx-4"
          inset="true"
          vertical="true"
        ></v-divider>

        <v-spacer></v-spacer>

        <v-dialog
          v-model="dialog"
          max-width="500px"
        >
          <!--    彈窗->新增按鈕 觸發器     -->
          <template v-slot:activator="{ props }">
            <v-btn
              color="primary"
              v-bind="props"
            >
              New Employee
            </v-btn>
          </template>

          <v-card>
            <v-card-title>
              <span v-if="editEvents">Edit Employee</span>
              <span v-else>New Employee</span>
            </v-card-title>

            <v-card-text>
              <v-expansion-panels>
                <v-expansion-panel>
                  <v-expansion-panel-title>
                    <template v-slot:default="{ expended }">
                      <v-row no-gutters>
                        <v-col cols="4" class="d-flex justify-lg-start">Name</v-col>
                        <v-col cols="8" class="text-black">
                          <v-fade-transition leave-absolute>
                            <span v-if="expended" key="0">Enter your name</span>
                            <span v-else key="1">{{ emp.name }}</span>
                          </v-fade-transition>
                        </v-col>
                      </v-row>
                    </template>
                  </v-expansion-panel-title>

                  <v-expansion-panel-text>
                    <v-text-field v-model="emp.name" placeholder="Enter your name." hide-details clearable="true"/>
                  </v-expansion-panel-text>
                </v-expansion-panel>

                <v-expansion-panel>
                  <v-expansion-panel-title v-slot="{ open }">
                    <v-row no-gutters>
                      <v-col cols="4" class="d-flex justify-start">
                        Role
                      </v-col>
                      <v-col cols="8" class="text-black">
                        <span v-if="open" key="0">Select your role.</span>
                        <span v-else key="1">{{ emp.role }}</span>
                      </v-col>
                    </v-row>
                  </v-expansion-panel-title>

                  <v-expansion-panel-text>
                    <v-radio-group v-model="emp.role" inline="true">
                      <v-radio label="frontend" value="frontend"></v-radio>
                      <v-radio label="backend" value="backend"></v-radio>
                      <v-radio label="uiux" value="uiux"></v-radio>
                      <v-radio label="pm" value="pm"></v-radio>
                      <v-radio label="qa" value="qa"></v-radio>
                      <v-radio label="sales" value="sales"></v-radio>
                    </v-radio-group>
                  </v-expansion-panel-text>
                </v-expansion-panel>

                <v-expansion-panel>
                  <v-expansion-panel-title v-slot="{ open }">
                    <v-row no-gutters>
                      <v-col cols="4" class="d-flex justify-start">
                        Gender
                      </v-col>
                      <v-col cols="8" class="text-black">
                        <span v-if="open" key="0">Select your gender.</span>
                        <span v-else key="1">{{ changGenderDataDisplay(emp.sex) }}</span>
                      </v-col>
                    </v-row>
                  </v-expansion-panel-title>

                  <v-expansion-panel-text>
                    <v-radio-group v-model="emp.sex" inline="true">
                      <v-radio label="Female" value="0"></v-radio>
                      <v-radio label="Male" value="1"></v-radio>
                      <v-radio label="Secret" value="2"></v-radio>
                    </v-radio-group>
                  </v-expansion-panel-text>
                </v-expansion-panel>

                <v-expansion-panel>
                  <v-expansion-panel-title v-slot="{ open }" disabled="disabled">
                    <v-row no-gutters>
                      <v-col cols="4" class="d-flex justify-start">
                        Department
                      </v-col>
                      <v-col cols="8" class="text-black">
                        <span>RCF</span>
                      </v-col>
                    </v-row>
                  </v-expansion-panel-title>
                </v-expansion-panel>
              </v-expansion-panels>
            </v-card-text>

            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn color="blue-darken-1" @click="close">Cancel</v-btn>
              <v-btn color="blue-darken-1" @click="save">Save</v-btn>
            </v-card-actions>
          </v-card>
        </v-dialog>

        <v-dialog v-model="dialogDelete" max-width="500px">
          <v-card>
            <v-card-title class="text-h5">Are you sure to delete <strong>{{ String(emp.name).toUpperCase() }}</strong> ?
            </v-card-title>
            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn color="blue-darken-1" @click="closeDelete">Cancel</v-btn>
              <v-btn color="blue-darken-1" @click="deleteItemConfirm">OK</v-btn>
            </v-card-actions>
          </v-card>
        </v-dialog>

      </v-toolbar>
    </template>

    <!--  性別  -->
    <template v-slot:item.sex="{ value }">
      <v-chip :color="getColor(value)">
        <span v-if="value === 1">Male</span>
        <span v-else-if="value === 0">Female</span>
        <span v-else>Secret</span>
      </v-chip>
    </template>

    <!--  建立時間  -->
    <template v-slot:item.createTime="{ value }">
      <span>
        {{ frontendDate(value) }}
      </span>
    </template>

    <!--  修改時間  -->
    <template v-slot:item.updateTime="{ value }">
      <span>
        {{ frontendDate(value) }}
      </span>
    </template>

    <!--  部門  -->
    <template v-slot:item.deptId="{ value }">
      <v-chip>
        <span v-if="value === 1">RCF</span>
      </v-chip>
    </template>

    <!--  操作  -->
    <template v-slot:item.actions="{ item }">
      <v-btn
        icon="mdi-pencil"
        size="small"
        class="me-2"
        @click="editItem(item)"
      ></v-btn>
      <v-btn
        icon="mdi-delete"
        size="small"
        @click="deleteItem(item)"
      ></v-btn>
    </template>
  </v-data-table-server>
</template>

<style scoped>

</style>
