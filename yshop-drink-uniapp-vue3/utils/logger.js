export const IS_DEV = process.env.NODE_ENV !== 'production'

const noop = () => {}

function createLogMethod(method) {
  if (IS_DEV) {
    return (...args) => console[method](...args)
  }
  return noop
}

export const logger = {
  debug: createLogMethod('log'),
  info: createLogMethod('info'),
  warn: createLogMethod('warn'),
  error: createLogMethod('error'),
}
