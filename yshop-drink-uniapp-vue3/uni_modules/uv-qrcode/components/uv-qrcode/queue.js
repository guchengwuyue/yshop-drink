function Queue() {
  let waitingQueue = this.waitingQueue = [];
  let isRunning = this.isRunning = false; // 记录是否有未完成的任务

  function execute(task, resolve, reject) {
    task()
      .then((data) => {
        resolve(data);
      })
      .catch((e) => {
        reject(e);
      })
      .finally(() => {
        // 等待任务队列中如果有任务，则触发它；否则设置isRunning = false,表示无任务状态
        if (waitingQueue.length) {
          const next = waitingQueue.shift();
          execute(next.task, next.resolve, next.reject);
        } else {
          isRunning = false;
        }
      });
  }
  this.exec = function(task) {
    return new Promise((resolve, reject) => {
      if (isRunning) {
        waitingQueue.push({
          task,
          resolve,
          reject
        });
      } else {
        isRunning = true;
        execute(task, resolve, reject);
      }
    });
  }
}

/* 队列实例，某些平台一起使用多个组件时需要通过队列逐一绘制，否则部分绘制方法异常，nvue端的iOS gcanvas尤其明显，在不通过队列绘制时会出现图片丢失的情况 */
export const queueDraw = new Queue();
export const queueLoadImage = new Queue();